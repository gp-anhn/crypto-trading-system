package com.example.cryptotradingsystem.dtos;

import com.example.cryptotradingsystem.enums.Action;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDTO {
    String currency;
    Action action;
    double amount;
}