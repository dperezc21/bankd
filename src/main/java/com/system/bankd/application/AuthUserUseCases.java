package com.system.bankd.application;

import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.AuthUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthUserUseCases implements AuthUserRepository {

    @Override
    public void registerUser(User user) {

    }

    @Override
    public User login(String username, String password) {
        return null;
    }
}
