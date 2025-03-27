package com.system.bankd.user.infrastructure;

import com.system.bankd.user.domain.User;
import com.system.bankd.user.domain.repositories.UserRepository;
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
