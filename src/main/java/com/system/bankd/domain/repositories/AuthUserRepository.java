package com.system.bankd.domain.repositories;

import com.system.bankd.domain.models.User;

public interface AuthUserRepository {
    void registerUser(User user);
    User login(String username, String password);
}
