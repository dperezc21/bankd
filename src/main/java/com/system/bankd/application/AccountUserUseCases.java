package com.system.bankd.application;

import com.system.bankd.domain.enums.AccountType;
import com.system.bankd.domain.models.Account;
import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountUserUseCases {

    @Autowired private UserAccountRepository userAccountRepository;

    public Account saveUserAccount(AccountType accountType, User user) {
        Account account = this.getAccountByTypeAndUserId(user.getUserId(), accountType);
        if(account == null) account = new Account();
        account.setAccountNumber("accountNumber");
        account.setAccountType(accountType);
        account.setUser(user);
        userAccountRepository.saveUserAccount(account);
        return account;
    }

    public Account getAccountById(Long accountId) {
        return userAccountRepository.getUserAccountById(accountId);
    }

    public Account getAccountByTypeAndUserId(Long userId, AccountType accountType) {
        return userAccountRepository.getAccountByTypeAndUserId(userId, accountType);
    }
}
