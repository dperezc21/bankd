package com.system.bankd.application;

import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUseCases {

    @Autowired private UserRepository userRepository;

    public User getUSerById(Long userId) {
        return this.userRepository.findUserById(userId);
    }
}
