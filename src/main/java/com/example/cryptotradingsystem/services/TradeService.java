package com.example.cryptotradingsystem.services;

import com.example.cryptotradingsystem.dtos.TradeDTO;
import com.example.cryptotradingsystem.entities.AggregatedPrice;
import com.example.cryptotradingsystem.enums.Currency;
import com.example.cryptotradingsystem.enums.Action;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeService {
    @Autowired
    private PriceService priceService;
    @Autowired
    private WalletService walletService;

    private static final Long USER_ID = 1L; // Demo user ID

    public void trade(TradeDTO tradeDTO) {
        AggregatedPrice price = priceService.getLatestPrice(tradeDTO.getCurrency());
        if (price == null) {
            throw new RuntimeException("No price available");
        }

        switch (tradeDTO.getAction()) {
            case Action.BUY:
                walletService.updateWalletBalances(USER_ID, Currency.USDT.name(), tradeDTO.getAmount() * price.getBestAsk(), tradeDTO.getCurrency(), tradeDTO.getAmount());
                break;
            case Action.SELL:
                walletService.updateWalletBalances(USER_ID, tradeDTO.getCurrency(), tradeDTO.getAmount(), Currency.USDT.name(), tradeDTO.getAmount() * price.getBestBid());
                break;
            default:
                break;
        }
    }
}
