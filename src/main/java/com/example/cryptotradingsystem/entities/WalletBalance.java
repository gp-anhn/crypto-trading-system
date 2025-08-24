package com.example.cryptotradingsystem.entities;

import com.google.gson.JsonObject;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class WalletBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String currency;
    private Double balance;

    protected WalletBalance() {
    }

    public WalletBalance(Long userId, String currency, Double balance) {
        this.userId = userId;
        this.currency = currency;
        this.balance = balance;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("currency", currency);
        jsonObject.addProperty("balance", balance);

        return jsonObject;
    }
}
