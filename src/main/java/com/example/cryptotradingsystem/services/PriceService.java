package com.example.cryptotradingsystem.services;

import com.example.cryptotradingsystem.entities.AggregatedPrice;
import com.example.cryptotradingsystem.repositories.AggregatedPriceRepository;
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
}
