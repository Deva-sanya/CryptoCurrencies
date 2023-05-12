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

    public String getJSON(int coinId) {
        String url = "https://api.coinlore.net/api/ticker/?id=" + coinId;
        return this.restTemplate.getForObject(url, String.class);
    }

    public void parseJson() {

    }

    public Double getPriceForCurrency(int coinId) {
        Optional<Currency> currencyFromDB = currencyRepository.findByCoinNum(coinId);
        double price = 0.0;
        if (currencyFromDB.isPresent()) {
            price = currencyFromDB.get().getPrice();
        }
        return price;
    }

    public Optional<Currency> findByName(String name) {
        return currencyRepository.findByName(name);
    }

    public Optional<Currency> findByCoinNum(int coinNum) {
        getJSON(coinNum);
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
