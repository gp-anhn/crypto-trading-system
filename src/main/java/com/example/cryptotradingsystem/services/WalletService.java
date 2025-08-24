package com.example.cryptotradingsystem.services;

import com.example.cryptotradingsystem.constants.Constant;
import com.example.cryptotradingsystem.entities.WalletBalance;
import com.example.cryptotradingsystem.enums.Currency;
import com.example.cryptotradingsystem.repositories.WalletBalanceRepository;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletService {
    @Autowired
    private WalletBalanceRepository walletBalanceRepository;

    public JsonArray getBalances() {
        JsonArray wallet = new JsonArray();
        walletBalanceRepository.findByUserId(Constant.USER_ID).forEach(balance -> {
            wallet.add(balance.toJson());
        });

        return wallet;
    }

    @Transactional
    public void updateWalletBalances(Long userId, Currency fromCurrency, double fromAmount,
                                     Currency toCurrency, double toAmount) {

        // Deduct fromCurrency
        WalletBalance fromWallet = walletBalanceRepository.findByUserIdAndCurrency(userId, fromCurrency.name())
                .orElseThrow(() -> new RuntimeException("Insufficient balance in " + fromCurrency));

        if (fromWallet.getBalance() < fromAmount) {
            throw new RuntimeException("Insufficient balance in " + fromCurrency);
        }
        fromWallet.setBalance(fromWallet.getBalance() - fromAmount);
        walletBalanceRepository.save(fromWallet);

        // Add toCurrency
        WalletBalance toWallet = walletBalanceRepository.findByUserIdAndCurrency(userId, toCurrency.name())
                .orElseGet(() -> walletBalanceRepository.save(new WalletBalance(Constant.USER_ID, toCurrency.name(), 0.0)));

        toWallet.setBalance(toWallet.getBalance() + toAmount);
        walletBalanceRepository.save(toWallet);
    }
}
