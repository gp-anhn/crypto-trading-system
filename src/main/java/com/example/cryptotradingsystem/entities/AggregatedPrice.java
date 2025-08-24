package com.example.cryptotradingsystem.entities;

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

    private String currency;
    private Double bestBid;
    private Double bestAsk;
    private LocalDateTime timestamp;

    public AggregatedPrice() {
    }
}
