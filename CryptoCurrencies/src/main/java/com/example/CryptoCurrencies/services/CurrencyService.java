package com.example.CryptoCurrencies.services;

import com.example.CryptoCurrencies.models.Currency;
import com.example.CryptoCurrencies.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, RestTemplate restTemplate) {
        this.currencyRepository = currencyRepository;
        this.restTemplate = restTemplate;
    }


    public String getPostsPlainJSON() {
        String url = "https://api.coinlore.net/api/tickers/";
        return this.restTemplate.getForObject(url, String.class);
    }

    public Optional<Currency> findByName(String name) {
        return currencyRepository.findByName(name);
    }

    public Optional<Currency> findByCoinNum(int coinNum) {
        return currencyRepository.findByCoinNum(coinNum);
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Transactional
    public void saveCurrency(Currency currency) {
        currencyRepository.save(currency);
    }

}
