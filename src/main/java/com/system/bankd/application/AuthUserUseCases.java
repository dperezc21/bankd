package com.system.bankd.application;

import com.system.bankd.domain.exceptions.DuplicateEntryException;
import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.AuthUserRepository;
import com.system.bankd.domain.repositories.CryptPasswordRepository;
import com.system.bankd.domain.responses.AuthUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserUseCases {

    @Autowired private AuthUserRepository authUserRepository;

    @Autowired private CryptPasswordRepository cryptRepository;

    public AuthUserResponse registerUser(User user) {
        try {
            String encodedPassword = cryptRepository.encryptPassword(user.getPassword());
            user.setPassword(encodedPassword);
            this.authUserRepository.registerUser(user);
        } catch (DuplicateEntryException e) {
            throw new DuplicateEntryException("entry duplicated, try with other data");
        }
        return mapAuthUser(user);
    }

    public AuthUserResponse login(String username, String password) {
        User userFound = this.authUserRepository.getUserByName(username);
        if(userFound == null) return null;
        Boolean verifyPassword = this.cryptRepository.verifyPassword(password, userFound.getPassword());
        return verifyPassword ? this.mapAuthUser(userFound) : null;
    }

    public AuthUserResponse mapAuthUser(User user) {
        return new AuthUserResponse(user.getUserId(), user.getUserName(), user.getFullName());
    }
}
