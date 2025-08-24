package com.example.cryptotradingsystem.dtos;

import com.example.cryptotradingsystem.enums.Action;
import com.example.cryptotradingsystem.enums.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDTO {
    Currency currency;
    Action action;
    double amount;
}