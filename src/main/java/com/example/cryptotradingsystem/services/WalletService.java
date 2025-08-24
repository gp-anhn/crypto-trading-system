package com.example.cryptotradingsystem.services;

import com.example.cryptotradingsystem.repositories.WalletBalanceRepository;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {
    @Autowired
    private WalletBalanceRepository walletBalanceRepository;

    public JsonArray getBalances() {
        JsonArray wallet = new JsonArray();
        walletBalanceRepository.findByUserId(1L).forEach(balance -> {
            wallet.add(balance.toJson());
        });

        return wallet;
    }
}
