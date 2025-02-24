package com.system.bankd.infrastructure.implementations;

import com.system.bankd.domain.enums.AccountType;
import com.system.bankd.domain.models.Account;
import com.system.bankd.domain.repositories.UserAccountRepository;
import com.system.bankd.infrastructure.database.MySqlUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountImpl implements UserAccountRepository {

    @Autowired private MySqlUserAccount mySqlUserAccount;

    @Override
    public void saveUserAccount(Account account) {
        this.mySqlUserAccount.save(account);
    }

    @Override
    public Account getUserAccountById(Long accountId) {
        return this.mySqlUserAccount.findById(accountId).orElse(null);
    }

    @Override
    public Account getAccountByTypeAndUserId(Long userId, AccountType accountType) {
        return this.mySqlUserAccount.getAccountByTypeAndUserId(userId, accountType.getValue());
    }
}
