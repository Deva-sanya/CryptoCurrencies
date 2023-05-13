package com.example.CryptoCurrencies.repositories;

import com.example.CryptoCurrencies.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findBySymbol(String symbol);
}
