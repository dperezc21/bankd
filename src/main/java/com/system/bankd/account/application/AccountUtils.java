package com.system.bankd.account.application;

import com.system.bankd.account.domain.Account;
import com.system.bankd.account.domain.Messages;
import com.system.bankd.account.domain.UserAccountRepository;
import com.system.bankd.account.domain.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountUtils {

    @Autowired
    private static UserAccountRepository userAccountRepository;

    public static Account getUserAccount(Long accountId) throws AccountNotFoundException {
        Account findAccount = userAccountRepository.getUserAccountById(accountId);
        if(findAccount == null) throw new AccountNotFoundException(Messages.ACCOUNT_NOT_FOUND_MESSAGE);
        return findAccount;
    }
}
