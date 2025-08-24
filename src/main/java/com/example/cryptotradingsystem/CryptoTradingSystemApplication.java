package com.example.cryptotradingsystem;

import com.example.cryptotradingsystem.constants.Constant;
import com.example.cryptotradingsystem.entities.WalletBalance;
import com.example.cryptotradingsystem.enums.Currency;
import com.example.cryptotradingsystem.repositories.WalletBalanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoTradingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoTradingSystemApplication.class, args);
    }


    // Initiate user wallet, assuming User's initial wallet balance is 50,000 USDT
    @Bean
    CommandLineRunner init(WalletBalanceRepository walletBalanceRepository) {
        return args -> {
            walletBalanceRepository.save(new WalletBalance(Constant.USER_ID, Currency.USDT.name(), 50000.0));
        };
    }
}
