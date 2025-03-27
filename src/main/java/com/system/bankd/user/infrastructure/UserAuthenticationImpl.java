package com.system.bankd.user.infrastructure;

import com.system.bankd.user.domain.User;
import com.system.bankd.user.domain.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthenticationImpl implements AuthUserRepository {

    @Autowired private MySqlAuthUser sqlAuthUser;

    @Override
    public void registerUser(User user) {
        this.sqlAuthUser.save(user);
    }

    @Override
    public User getUserByName(String username) {
        return this.sqlAuthUser.getUser(username);
    }
}
