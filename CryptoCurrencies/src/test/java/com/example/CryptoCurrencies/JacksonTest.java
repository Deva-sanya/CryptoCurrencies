package com.example.CryptoCurrencies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.CryptoCurrencies.models.Currency;

@RunWith(SpringRunner.class)
@SpringBootTest

public class JacksonTest {
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void jsonStringGetSymbol() throws JsonProcessingException {
        String url = "https://api.coinlore.net/api/ticker/?id=" + 90;
        String currencyJson = restTemplate.getForObject(url, String.class);
        System.out.println(currencyJson);

        Currency[] jsonObj = objectMapper.readValue(currencyJson, Currency[].class);
        for (Currency itr : jsonObj) {
            System.out.println("Symbol: " + itr.getSymbol());
        }
    }

    @Test
    void jsonStringGetPrice() throws JsonProcessingException {
        String url = "https://api.coinlore.net/api/ticker/?id=" + 90;
        String currencyJson = restTemplate.getForObject(url, String.class);
        System.out.println(currencyJson);

        Currency[] jsonObj = objectMapper.readValue(currencyJson, Currency[].class);
        for (Currency itr : jsonObj) {
            System.out.println("Price: " + itr.getPrice_usd());
        }
    }

}
