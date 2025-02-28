package com.system.bankd.domain.repositories;

import com.system.bankd.domain.models.User;

public interface UserRepository {
    User findUserById(Long userId);
}
