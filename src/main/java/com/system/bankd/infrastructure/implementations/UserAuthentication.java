package com.system.bankd.infrastructure.implementations;

import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.AuthUserRepository;
import com.system.bankd.infrastructure.database.MySqlAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthentication implements AuthUserRepository {

    @Autowired private MySqlAuthUser sqlAuthUser;

    @Override
    public void registerUser(User user) {
        this.sqlAuthUser.save(user);
    }

    @Override
    public User login(String username, String password) {
        return this.sqlAuthUser.getUser(username);
    }
}
