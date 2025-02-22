package com.system.bankd.application;

import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserUseCases {

    @Autowired private AuthUserRepository authUserRepository;

    public void registerUser(User user) {
        this.authUserRepository.registerUser(user);
    }

    public User login(String username, String password) {
        return null;
    }
}
