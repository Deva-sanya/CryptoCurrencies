package com.example.CryptoCurrencies.services;

import com.example.CryptoCurrencies.models.Currency;
import com.example.CryptoCurrencies.repositories.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

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

    public String getJSON(int id) {
        String url = "https://api.coinlore.net/api/ticker/?id="+ id;
        return this.restTemplate.getForObject(url, String.class);
    }

    public Double getPrice(String symbol) throws JsonProcessingException {
        Double price = 0.0;
        switch(symbol) {
            case "BTS":
                String jsonStr = getJSON(90);
                JsonNode obj = new ObjectMapper().readTree(jsonStr);
                price = obj.get("price_usd").asDouble();
                break;
            case "ETH":
                jsonStr = getJSON(80);
                obj = new ObjectMapper().readTree(jsonStr);
                price = obj.get("price_usd").asDouble();
                break;
            case "SOL":
                jsonStr = getJSON(48543);
                obj = new ObjectMapper().readTree(jsonStr);
                price = obj.get("price_usd").asDouble();
                break;
        }
        System.out.println(price);
        return price;
    }

    @Transactional
    public void savePrice(Currency currency, String symbol) throws JsonProcessingException {
        enrichPrice(currency,symbol);
        currencyRepository.save(currency);
    }

    public void enrichPrice(Currency currency, String symbol) throws JsonProcessingException {
        currency.setPrice(getPrice(symbol));
        currency.setPriceDateTime(LocalDateTime.now());
    }

    public Currency findBySymbol(String symbol) {
        return currencyRepository.findBySymbol(symbol);
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Transactional
    public void saveCurrency(Currency currency) {
        currencyRepository.save(currency);
    }

}
