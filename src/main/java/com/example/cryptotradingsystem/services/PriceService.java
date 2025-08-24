package com.example.cryptotradingsystem.services;

import com.example.cryptotradingsystem.entities.AggregatedPrice;
import com.example.cryptotradingsystem.enums.Currency;
import com.example.cryptotradingsystem.repositories.AggregatedPriceRepository;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {
    @Autowired
    private AggregatedPriceRepository aggregatedPriceRepository;

    public void save(AggregatedPrice price) {
        aggregatedPriceRepository.findAggregatedPriceByCurrency(price.getCurrency()).map(existing -> {
            existing.setBestBid(price.getBestBid());
            existing.setBestAsk(price.getBestAsk());
            existing.setTimestamp(LocalDateTime.now());

            return aggregatedPriceRepository.save(existing);
        }).orElseGet(() -> aggregatedPriceRepository.save(price));
    }

    public JsonArray getLatestPrice() {
        JsonArray data = new JsonArray();
        aggregatedPriceRepository.findAll().forEach(price -> {
            data.add(price.toJson());
        });
        return data;
    }

    public AggregatedPrice getLatestPrice(Currency currency) {
        return aggregatedPriceRepository.findAggregatedPriceByCurrency(currency).orElse(null);
    }
}
