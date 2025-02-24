package com.system.bankd.infrastructure.implementations;

import com.system.bankd.domain.enums.AccountType;
import com.system.bankd.domain.models.Account;
import com.system.bankd.domain.repositories.UserAccountRepository;
import com.system.bankd.infrastructure.database.MySqlAccountUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountImpl implements UserAccountRepository {

    @Autowired private MySqlAccountUser mySqlAccountUser;

    @Override
    public void saveUserAccount(Account account) {
        this.mySqlAccountUser.save(account);
    }

    @Override
    public Account getUserAccountById(Long accountId) {
        return this.mySqlAccountUser.findById(accountId).orElse(null);
    }

    @Override
    public Account getAccountByTypeAndUserId(Long userId, AccountType accountType) {
        return this.mySqlAccountUser.getAccountByTypeAndUserId(userId, accountType);
    }
}
