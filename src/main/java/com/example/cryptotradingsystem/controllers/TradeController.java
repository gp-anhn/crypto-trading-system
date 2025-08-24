package com.example.cryptotradingsystem.controllers;

import com.example.cryptotradingsystem.dtos.TradeDTO;
import com.example.cryptotradingsystem.enums.Action;
import com.example.cryptotradingsystem.services.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping("/trade")
    public ResponseEntity<String> trade(@RequestBody TradeDTO tradeDTO) {
        tradeService.trade(tradeDTO);
        String message = "";
        switch (tradeDTO.getAction()) {
            case Action.BUY:
                message = "bought";
                break;
            case Action.SELL:
                message = "sold";
                break;
            default:
                break;
        }
        return ResponseEntity.ok("Successfully " + message + " " + tradeDTO.getAmount() + " " + tradeDTO.getCurrency());
    }

    @GetMapping("/list")
    public ResponseEntity<String> list() {
        return ResponseEntity.ok(tradeService.getTransactionHistory().toString());
    }
}
