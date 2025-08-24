package com.example.cryptotradingsystem.controllers;

import com.example.cryptotradingsystem.dtos.TradeDTO;
import com.example.cryptotradingsystem.services.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trade")
@RequiredArgsConstructor
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping
    public ResponseEntity<String> getBalances(@RequestBody TradeDTO tradeDTO) {
        tradeService.trade(tradeDTO);
        return ResponseEntity.ok().build();
    }
}
