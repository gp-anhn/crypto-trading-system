package com.example.cryptotradingsystem.repositories;

import com.example.cryptotradingsystem.entities.WalletBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletBalanceRepository extends JpaRepository<WalletBalance, Long> {
    List<WalletBalance> findByUserId(Long userId);
    Optional<WalletBalance> findByUserIdAndCurrency(Long userId, String currency);
}
