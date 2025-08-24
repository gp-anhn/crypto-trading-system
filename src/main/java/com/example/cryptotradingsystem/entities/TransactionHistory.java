package com.example.cryptotradingsystem.entities;


import com.example.cryptotradingsystem.enums.Action;
import com.example.cryptotradingsystem.enums.Currency;
import com.google.gson.JsonObject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transId;

    private Long userId;
    private Currency currency;
    private Action action;
    private Double price;
    private Double amount;
    private LocalDateTime timestamp;

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("transId", transId);
        jsonObject.addProperty("currency", currency.name());
        jsonObject.addProperty("action", action.name());
        jsonObject.addProperty("price", price);
        jsonObject.addProperty("amount", amount);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jsonObject.addProperty("timestamp", formatter.format(timestamp));

        return jsonObject;
    }
}
