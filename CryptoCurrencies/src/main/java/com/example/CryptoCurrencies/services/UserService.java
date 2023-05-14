package com.example.CryptoCurrencies.services;

import com.example.CryptoCurrencies.models.User;
import com.example.CryptoCurrencies.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public User saveUser(User user) {
        return usersRepository.save(user);
    }

}
