package com.system.bankd.application;

import com.system.bankd.domain.enums.AccountType;
import com.system.bankd.domain.exceptions.AccountTransactionException;
import com.system.bankd.domain.models.Account;
import com.system.bankd.domain.models.User;
import com.system.bankd.domain.repositories.UserAccountRepository;
import com.system.bankd.domain.responses.AccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountUseCases {

    @Autowired private UserAccountRepository userAccountRepository;
    @Autowired private MovementUseCase movementUseCase;

    public AccountTransaction saveUserAccount(AccountType accountType, User user) {
        Account account = this.getAccountByTypeAndUserId(user.getUserId(), accountType);
        if(account != null) return this.mapAccountTransaction(account);
        account = new Account();
        account.setAccountNumber(GenerateId.generateId());
        account.setAccountType(accountType.getValue());
        account.setAccountAmount(0.0);
        account.setUser(user);
        userAccountRepository.saveUserAccount(account);
        return this.mapAccountTransaction(account);
    }

    public Account getAccountById(Long accountId) {
        return userAccountRepository.getUserAccountById(accountId);
    }

    public Account getAccountByTypeAndUserId(Long userId, AccountType accountType) {
        return userAccountRepository.getAccountByTypeAndUserId(userId, accountType);
    }

    public AccountTransaction accountDeposit(Long userId, Long accountId, Double amount) throws AccountTransactionException {
        Account accountToDeposit = this.verifyAccountToTransaction(accountId, userId);
        if(accountToDeposit == null) throw new AccountTransactionException("account to deposit not found");
        accountToDeposit.setAccountAmount(accountToDeposit.getAccountAmount() + amount);
        this.userAccountRepository.deposit(accountToDeposit);
        this.movementUseCase.saveMovement("deposit", "send amount", amount, accountToDeposit);
        return this.mapAccountDeposit(accountToDeposit);
    }

    public AccountTransaction withdrawInUserAccount(Long userId, Long accountId, Double amount) throws AccountTransactionException {
        Account accountToWithdraw = this.verifyAccountToTransaction(accountId, userId);
        if(accountToWithdraw == null) throw new AccountTransactionException("account to withdraw not found");
        boolean validAmountAllowed = amount <= accountToWithdraw.getAccountAmount();
        if(!validAmountAllowed) throw new AccountTransactionException("amount greater that account amount current");
        accountToWithdraw.setAccountAmount(accountToWithdraw.getAccountAmount() - amount);
        this.userAccountRepository.deposit(accountToWithdraw);
        this.movementUseCase.saveMovement("take out", "take out in toronto branch", amount * -1, accountToWithdraw);
        return this.mapAccountDeposit(accountToWithdraw);
    }

    public Account verifyAccountToTransaction(Long accountId, Long userId) {
        Account accountToDeposit = this.userAccountRepository.getUserAccountById(accountId);
        if(accountToDeposit == null) return null;
        AccountType accountType = AccountType.valueOf(accountToDeposit.getAccountType().toUpperCase());
        accountToDeposit = this.userAccountRepository.getAccountByTypeAndUserId(userId, accountType);
        return accountToDeposit;
    }

    public AccountTransaction mapAccountDeposit(Account account) {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccountId(account.getAccountId());
        accountTransaction.setAmount(account.getAccountAmount());
        accountTransaction.setUserId(account.getUser().getUserId());
        return accountTransaction;
    }

    private AccountTransaction mapAccountTransaction(Account account) {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccountId(account.getAccountId());
        accountTransaction.setAmount(account.getAccountAmount());
        accountTransaction.setUserId(account.getUser().getUserId());
        accountTransaction.setAccountType(account.getAccountType());
        return accountTransaction;
    }
}
