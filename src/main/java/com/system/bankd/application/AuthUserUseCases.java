package com.system.bankd.application;

import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.AuthUserRepository;
import com.system.bankd.domain.repositories.CryptPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserUseCases {

    @Autowired private AuthUserRepository authUserRepository;

    @Autowired private CryptPasswordRepository cryptRepository;

    public User registerUser(User user) {
        String encodedPassword = cryptRepository.encryptPassword(user.getPassword());
        user.setPassword(encodedPassword);
        this.authUserRepository.registerUser(user);
        return user;
    }

    public User login(String username, String password) {
        return null;
    }
}
