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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "Name of sensor should not be empty")
    @Size(min = 1, max = 100, message = "The name of the currency should be between 1 and 100 characters long.")
    private String name;

    @Column(name = "price")
    @NotNull(message = "Value of price should not be empty")
    private Double price;

    @Column(name = "coin_id")
    @NotEmpty(message = "Coin id of currency should not be empty")
    private Integer coinNum;

}
