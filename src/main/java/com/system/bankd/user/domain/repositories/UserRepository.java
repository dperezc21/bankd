package com.system.bankd.user.domain.repositories;

import com.system.bankd.user.domain.User;

public interface UserRepository {
    User findUserById(Long userId);
}
