package com.system.bankd.account.infrastructure;

import com.system.bankd.account.domain.AccountType;
import com.system.bankd.account.domain.Account;
import com.system.bankd.account.domain.UserAccountRepository;
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

    @Override
    public void deposit(Account account) {
        this.saveUserAccount(account);
    }
}
