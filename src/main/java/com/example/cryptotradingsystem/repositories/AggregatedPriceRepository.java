package com.example.cryptotradingsystem.repositories;

import com.example.cryptotradingsystem.entities.AggregatedPrice;
import com.example.cryptotradingsystem.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AggregatedPriceRepository extends JpaRepository<AggregatedPrice, Long> {
    Optional<AggregatedPrice> findAggregatedPriceByCurrency(Currency currency);
}
