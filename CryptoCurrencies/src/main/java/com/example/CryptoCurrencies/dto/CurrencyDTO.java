package com.example.CryptoCurrencies.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class CurrencyDTO {

    @NotEmpty(message = "Symbol of currency should not be empty")
    @Size(min = 1, max = 3, message = "The symbol of the currency should be between 1 and 3 characters long.")
    private String symbol;

    @NotNull(message = "Value of price should not be empty")
    private Double price_usd;

    private LocalDateTime priceDateTime;

}
