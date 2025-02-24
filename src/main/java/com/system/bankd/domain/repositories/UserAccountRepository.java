package com.system.bankd.domain.repositories;

import com.system.bankd.domain.enums.AccountType;
import com.system.bankd.domain.models.Account;

public interface UserAccountRepository {
    void saveUserAccount(Account account);
    Account getUserAccountById(Long accountId);
    Account getAccountByTypeAndUserId(Long userId, AccountType accountType);
}
