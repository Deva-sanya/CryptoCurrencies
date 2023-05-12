package com.example.CryptoCurrencies.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CurrencyDTO {

    @NotEmpty(message = "Name of currency should not be empty")
    @Size(min = 1, max = 100, message = "The name of the currency should be between 1 and 100 characters long.")
    private String name;

    @NotNull(message = "Value of price should not be empty")
    double price;

    @NotEmpty(message = "Coin id of currency should not be empty")
    private int coinNum;

}
