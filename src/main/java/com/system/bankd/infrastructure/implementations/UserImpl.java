package com.system.bankd.infrastructure.implementations;

import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.UserRepository;
import com.system.bankd.infrastructure.database.MySqlAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserImpl implements UserRepository {

    @Autowired private MySqlAuthUser sqlAuthUser;

    @Override
    public User findUserById(Long userId) {
        return this.sqlAuthUser.findById(userId).orElse(null);
    }
}
