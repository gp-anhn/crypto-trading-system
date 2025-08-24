package com.example.cryptotradingsystem.schedulers;

import com.example.cryptotradingsystem.entities.AggregatedPrice;
import com.example.cryptotradingsystem.enums.Currency;
import com.example.cryptotradingsystem.services.PriceService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PriceScheduler {
    @Autowired
    private PriceService priceService;
    private final Gson gson = new Gson();
    private final RestTemplate restTemplate = new RestTemplate();

    // Fetch new data every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void fetchPrices() {
        fetchAndSave(Currency.BTC);
        fetchAndSave(Currency.ETH);
    }

    private void fetchAndSave(Currency currency) {
        try {
            // Binance
            String binanceUrl = "https://api.binance.com/api/v3/ticker/bookTicker?symbol=" + currency + Currency.USDT;
            JsonObject binance = gson.fromJson(restTemplate.getForObject(binanceUrl, String.class), JsonObject.class);

            double binanceBid = binance.get("bidPrice").getAsDouble();
            double binanceAsk = binance.get("askPrice").getAsDouble();

            // Huobi
            String huobiUrl = "https://api.huobi.pro/market/tickers";
            JsonObject huobi = gson.fromJson(restTemplate.getForObject(huobiUrl, String.class), JsonObject.class);
            JsonArray data = huobi.getAsJsonArray("data");

            double huobiBid = 0, huobiAsk = Double.MAX_VALUE;
            for (int i = 0; i < data.size(); i++) {
                JsonObject obj = data.get(i).getAsJsonObject();
                if (obj.get("symbol").toString().equalsIgnoreCase((currency.name() + Currency.USDT.name()).toLowerCase())) {
                    huobiBid = obj.get("bid").getAsDouble();
                    huobiAsk = obj.get("ask").getAsDouble();
                    break;
                }
            }

            double bestBid = Math.max(binanceBid, huobiBid);
            double bestAsk = Math.min(binanceAsk, huobiAsk);

            AggregatedPrice agg = AggregatedPrice.builder()
                    .symbol(currency.name() + Currency.USDT.name())
                    .bestBid(bestBid)
                    .bestAsk(bestAsk)
                    .timestamp(LocalDateTime.now())
                    .build();

            priceService.save(agg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
