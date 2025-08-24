package com.example.cryptotradingsystem.controllers;

import com.example.cryptotradingsystem.services.WalletService;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {
    @Autowired
    private WalletService service;

    @GetMapping
    public ResponseEntity<String> getBalances() {
        JsonArray wallet = new JsonArray();
        service.getBalances(1L).forEach(balance -> {
            wallet.add(balance.toJson());
        });

        return ResponseEntity.ok(wallet.toString());
    }
}
