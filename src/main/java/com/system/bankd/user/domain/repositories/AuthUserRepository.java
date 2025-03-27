package com.system.bankd.user.domain.repositories;

import com.system.bankd.user.domain.User;

public interface AuthUserRepository {
    void registerUser(User user);
    User getUserByName(String username);
}
