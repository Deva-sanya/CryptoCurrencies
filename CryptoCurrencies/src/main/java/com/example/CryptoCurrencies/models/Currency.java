package com.example.CryptoCurrencies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "crypto_currency")
public class Currency implements Serializable {
    @Id
    @JsonIgnore
    @Column(name = "id")
    private Integer id;

    @Column(name = "symbol")
    @NotEmpty(message = "Symbol of currency should not be empty")
    @Size(min = 1, max = 3, message = "The symbol of the currency should be between 1 and 100 characters long.")
    private String symbol;

    @Column(name = "price")
    @NotNull(message = "Value of price should not be empty")
    private Double price;


}
