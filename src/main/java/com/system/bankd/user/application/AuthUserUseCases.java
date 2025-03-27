package com.system.bankd.user.application;

import com.system.bankd.account.application.UserAccountUseCases;
import com.system.bankd.account.domain.AccountType;
import com.system.bankd.user.domain.exceptions.DuplicateEntryException;
import com.system.bankd.user.domain.exceptions.UserNotFoundException;
import com.system.bankd.user.domain.User;
import com.system.bankd.user.domain.repositories.AuthUserRepository;
import com.system.bankd.user.domain.repositories.CryptPasswordRepository;
import com.system.bankd.user.domain.AuthUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserUseCases {

    @Autowired private AuthUserRepository authUserRepository;

    @Autowired private CryptPasswordRepository cryptRepository;

    @Autowired private UserAccountUseCases userAccountUseCases;

    public AuthUserResponse registerUser(User user) throws UserNotFoundException {
        try {
            String encodedPassword = cryptRepository.encryptPassword(user.getPassword());
            user.setPassword(encodedPassword);
            this.authUserRepository.registerUser(user);
            this.userAccountUseCases.saveUserAccount(AccountType.SAVING, user.getUserId());
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
