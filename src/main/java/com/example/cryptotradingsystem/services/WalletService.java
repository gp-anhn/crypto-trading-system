package com.example.cryptotradingsystem.services;

import com.example.cryptotradingsystem.entities.WalletBalance;
import com.example.cryptotradingsystem.repositories.WalletBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    @Autowired
    private WalletBalanceRepository repo;

    public List<WalletBalance> getBalances(Long userId) {
        return repo.findByUserId(userId);
    }
}
