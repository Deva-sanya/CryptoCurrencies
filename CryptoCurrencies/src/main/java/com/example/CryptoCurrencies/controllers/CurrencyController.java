package com.example.CryptoCurrencies.controllers;

import com.example.CryptoCurrencies.models.Currency;
import com.example.CryptoCurrencies.services.CurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/all")
    public List<Currency> allCurrencies() {
        return currencyService.findAll();
    }

    @GetMapping("/allEnum")
    public List<Currency> allCurrenciesEnum() {
        return currencyService.allCurrency();
    }

    @GetMapping("/symbol")
    public Currency findBySymbol(@RequestParam("symbol") String symbol) {
        return currencyService.findBySymbol(symbol);
    }


    @Scheduled(fixedRate = 60000)
    @PostMapping(value = "/getPrice")
    public ResponseEntity<HttpStatus> getPrice() throws JsonProcessingException {
        List<Currency> currencies = currencyService.findAll();
        List symbolDB = new ArrayList();

        for (int i = 0; i < currencies.size(); i++) {
            Currency currencyDB = currencies.get(i);

            symbolDB.add(currencies.get(i).getSymbol());
            String symbolDBSTR = (String) symbolDB.get(i);
            currencyService.savePrice(currencyDB,symbolDBSTR);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/price")
    public Double findPriceBySymbol(@RequestParam("symbol") String symbol) {
        return currencyService.getPriceBySymbol(symbol);
    }

}
