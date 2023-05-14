package com.example.CryptoCurrencies.controllers;

import com.example.CryptoCurrencies.models.Currency;
import com.example.CryptoCurrencies.models.User;
import com.example.CryptoCurrencies.services.CurrencyService;
import com.example.CryptoCurrencies.services.UserService;
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
    private final UserService userService;

    @Autowired
    public CurrencyController(CurrencyService currencyService, UserService userService) {
        this.currencyService = currencyService;
        this.userService = userService;
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
    @PostMapping("/getPrice")
    public ResponseEntity<HttpStatus> getPrice() throws JsonProcessingException {
        List<Currency> currencies = currencyService.findAll();
        List symbolDB = new ArrayList();

        for (int i = 0; i < currencies.size(); i++) {
            Currency currencyDB = currencies.get(i);

            symbolDB.add(currencies.get(i).getSymbol());
            String symbolDBSTR = (String) symbolDB.get(i);
            currencyService.savePrice(currencyDB, symbolDBSTR);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/notify")
    public String notify(@RequestParam("name") String name, @RequestParam("symbol") String symbol) {
        User user = new User();
        user.setName(name);
        userService.saveUser(user);

        String hello = "Hello, " + name + " you checked currency with symbol " + symbol + ". Current price: " +
                currencyService.getPriceBySymbol(symbol);
        return hello;
    }


    @GetMapping("/price")
    public Double findPriceBySymbol(@RequestParam("symbol") String symbol) {
        return currencyService.getPriceBySymbol(symbol);
    }

}
