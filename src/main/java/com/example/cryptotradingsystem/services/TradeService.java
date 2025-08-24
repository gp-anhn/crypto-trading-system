package com.example.cryptotradingsystem.services;

import com.example.cryptotradingsystem.constants.Constant;
import com.example.cryptotradingsystem.dtos.TradeDTO;
import com.example.cryptotradingsystem.entities.AggregatedPrice;
import com.example.cryptotradingsystem.entities.TransactionHistory;
import com.example.cryptotradingsystem.enums.Currency;
import com.example.cryptotradingsystem.enums.Action;
import com.example.cryptotradingsystem.repositories.TransactionHistoryRepository;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TradeService {
    @Autowired
    private PriceService priceService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    public void trade(TradeDTO tradeDTO) {
        AggregatedPrice price = priceService.getLatestPrice(tradeDTO.getCurrency().name() + Currency.USDT.name());
        if (price == null) {
            throw new RuntimeException("No price available");
        }

        TransactionHistory.TransactionHistoryBuilder transactionHistoryBuilder = TransactionHistory.builder()
                .userId(Constant.USER_ID)
                .currency(tradeDTO.getCurrency())
                .action(tradeDTO.getAction())
                .amount(tradeDTO.getAmount())
                .timestamp(LocalDateTime.now());

        switch (tradeDTO.getAction()) {
            case Action.BUY:
                walletService.updateWalletBalances(Constant.USER_ID, Currency.USDT, tradeDTO.getAmount() * price.getBestAsk(), tradeDTO.getCurrency(), tradeDTO.getAmount());
                transactionHistoryBuilder.price(price.getBestAsk());
                break;
            case Action.SELL:
                walletService.updateWalletBalances(Constant.USER_ID, tradeDTO.getCurrency(), tradeDTO.getAmount(), Currency.USDT, tradeDTO.getAmount() * price.getBestBid());
                transactionHistoryBuilder.price(price.getBestBid());
                break;
            default:
                break;
        }

        transactionHistoryRepository.save(transactionHistoryBuilder.build());
    }

    public JsonArray getTransactionHistory() {
        JsonArray data = new JsonArray();
        transactionHistoryRepository.findByUserId(Constant.USER_ID).forEach(trans -> {
            data.add(trans.toJson());
        });
        return data;
    }
}
