package com.example.CryptoCurrencies.services;

import com.example.CryptoCurrencies.models.Currency;
import com.example.CryptoCurrencies.repositories.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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

    public String getJSON(int id) {
        String url = "https://api.coinlore.net/api/ticker/?id="+ id;
        return this.restTemplate.getForObject(url, String.class);
    }

    public Double getPrice(int id) throws JsonProcessingException {
        String jsonStr = getJSON(id);
        JsonNode parent= new ObjectMapper().readTree(jsonStr);
        Double price = parent.get("price_usd").asDouble();

        System.out.println(price);
        return price;
    }

    @Transactional
    public void savePrice(Currency currency, int id) throws JsonProcessingException {
        enrichPrice(currency, id);
        currencyRepository.save(currency);
    }

    public void enrichPrice(Currency currency, int id) throws JsonProcessingException {
        currency.setPrice(getPrice(id));
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
