package com.example.CryptoCurrencies.repositories;

import com.example.CryptoCurrencies.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Currency findBySymbol(String symbol);
}
