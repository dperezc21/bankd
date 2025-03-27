package com.system.bankd.account.domain;

public interface UserAccountRepository {
    void saveUserAccount(Account account);
    Account getUserAccountById(Long accountId);
    Account getAccountByTypeAndUserId(Long userId, AccountType accountType);
    void deposit(Account account);
}
