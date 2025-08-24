package com.example.cryptotradingsystem.entities;

import com.google.gson.JsonObject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class AggregatedPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private Double bestBid;
    private Double bestAsk;
    private LocalDateTime timestamp;

    public AggregatedPrice() {
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("symbol", symbol);
        jsonObject.addProperty("bestBid", bestBid);
        jsonObject.addProperty("bestAsk", bestAsk);
        jsonObject.addProperty("timestamp", String.valueOf(timestamp));

        return jsonObject;
    }
}
