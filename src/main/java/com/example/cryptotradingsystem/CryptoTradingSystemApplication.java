package com.example.cryptotradingsystem;

import com.example.cryptotradingsystem.entities.WalletBalance;
import com.example.cryptotradingsystem.repositories.WalletBalanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptoTradingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoTradingSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner init(WalletBalanceRepository walletBalanceRepository) {
        return args -> {
            walletBalanceRepository.save(new WalletBalance( 1L, "USDT", 50000.0));
            walletBalanceRepository.save(new WalletBalance(1L, "BTC", 0.0));
            walletBalanceRepository.save(new WalletBalance(1L, "ETH", 0.0));
        };
    }
}
