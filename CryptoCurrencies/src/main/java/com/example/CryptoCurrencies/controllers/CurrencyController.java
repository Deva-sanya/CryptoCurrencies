package com.example.CryptoCurrencies.controllers;

import com.example.CryptoCurrencies.dto.CurrencyDTO;
import com.example.CryptoCurrencies.models.Currency;
import com.example.CryptoCurrencies.services.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CurrencyController(CurrencyService currencyService, ModelMapper modelMapper) {
        this.currencyService = currencyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<Currency> allCurrencies() {
        return currencyService.findAll();
    }

    @GetMapping("/symbol")
    public Optional<Currency> findByName(@RequestParam("symbol") String symbol) {
        return currencyService.findBySymbol(symbol);
    }

    @PostMapping(value = "/getPrice")
    public ResponseEntity<HttpStatus> saveCurrentPrice(@RequestBody @Valid CurrencyDTO currencyDTO, BindingResult bindingResult) {
        Currency currencyToAdd = convertToCurrency(currencyDTO);
        currencyService.savePrice(currencyToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Currency convertToCurrency(CurrencyDTO currencyDTO) {
        return modelMapper.map(currencyDTO, Currency.class);
    }

    private CurrencyDTO convertToCurrencyDTO(Currency currency) {
        return modelMapper.map(currency, CurrencyDTO.class);
    }

}
