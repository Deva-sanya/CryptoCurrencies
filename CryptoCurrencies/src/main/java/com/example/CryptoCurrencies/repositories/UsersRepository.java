package com.example.CryptoCurrencies.repositories;

import com.example.CryptoCurrencies.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
