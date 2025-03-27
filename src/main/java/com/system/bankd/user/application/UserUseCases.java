package com.system.bankd.user.application;

import com.system.bankd.user.domain.User;
import com.system.bankd.user.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUseCases {

    @Autowired private UserRepository userRepository;

    public User getUSerById(Long userId) {
        return this.userRepository.findUserById(userId);
    }
}
