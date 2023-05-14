package com.example.CryptoCurrencies.services;

import com.example.CryptoCurrencies.models.Currency;
import com.example.CryptoCurrencies.repositories.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    public String getJSONStr(int id) {
        String url = "https://api.coinlore.net/api/ticker/?id=" + id;
        return restTemplate.getForObject(url, String.class);
    }

    public Double getPrice(String symbol) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Double price = 0.0;
        switch (symbol) {
            case "BTC":
                String jsonStr = getJSONStr(90);
                Currency[] jsonObj = mapper.readValue(jsonStr, Currency[].class);
                for (Currency itr : jsonObj) {
                    price = itr.getPrice_usd();
                    System.out.println("Price: " + itr.getPrice_usd());
                }
                break;
            case "ETH":
                jsonStr = getJSONStr(80);
                jsonObj = mapper.readValue(jsonStr, Currency[].class);
                for (Currency itr : jsonObj) {
                    price = itr.getPrice_usd();
                    System.out.println("Price: " + itr.getPrice_usd());
                }
                break;
            case "SOL":
                jsonStr = getJSONStr(48543);
                jsonObj = mapper.readValue(jsonStr, Currency[].class);
                for (Currency itr : jsonObj) {
                    price = itr.getPrice_usd();
                    System.out.println("Price: " + itr.getPrice_usd());
                }
        }
        return price;
    }


    @Transactional
    public Currency savePrice(Currency currency, String symbol) throws JsonProcessingException {
        enrichPrice(currency, symbol);
        currencyRepository.save(currency);
        return currency;
    }

    public void enrichPrice(Currency currency, String symbol) throws JsonProcessingException {
        currency.setPrice_usd(getPrice(symbol));
        currency.setPriceDateTime(LocalDateTime.now());
    }

    public Currency findBySymbol(String symbol) {
        return currencyRepository.findBySymbol(symbol);
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

}
