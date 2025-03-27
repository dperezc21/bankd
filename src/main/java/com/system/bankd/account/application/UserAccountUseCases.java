package com.system.bankd.account.application;

import com.system.bankd.movement.application.MovementUseCase;
import com.system.bankd.account.domain.Messages;
import com.system.bankd.account.domain.AccountType;
import com.system.bankd.account.domain.exceptions.AccountTransactionException;
import com.system.bankd.user.domain.exceptions.UserNotFoundException;
import com.system.bankd.account.domain.Account;
import com.system.bankd.user.domain.User;
import com.system.bankd.account.domain.UserAccountRepository;
import com.system.bankd.user.domain.repositories.UserRepository;
import com.system.bankd.account.domain.AccountTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountUseCases {

    @Autowired private UserAccountRepository userAccountRepository;
    @Autowired private MovementUseCase movementUseCase;
    @Autowired private UserRepository userRepository;

    public AccountTransactionResponse saveUserAccount(AccountType accountType, Long userId) throws UserNotFoundException {
        User findUser = this.userRepository.findUserById(userId);
        if(findUser == null) throw new UserNotFoundException("user not found");
        Account account = this.getAccountByTypeAndUserId(userId, accountType);
        if(account != null) return this.mapAccountTransaction(account);
        account = new Account();
        account.setAccountNumber(GenerateId.generateId());
        account.setAccountType(accountType.getValue());
        account.setAccountAmount(0.0);
        account.setUser(findUser);
        userAccountRepository.saveUserAccount(account);
        return this.mapAccountTransaction(account);
    }

    public Account getAccountByTypeAndUserId(Long userId, AccountType accountType) {
        return userAccountRepository.getAccountByTypeAndUserId(userId, accountType);
    }

    public AccountTransactionResponse accountDeposit(Long userId, Long accountId, Double amount) throws AccountTransactionException {
        Account accountToDeposit = this.verifyAccountToTransaction(accountId, userId);
        if(accountToDeposit == null) throw new AccountTransactionException("account to deposit not found");
        accountToDeposit.setAccountAmount(accountToDeposit.getAccountAmount() + amount);
        this.userAccountRepository.deposit(accountToDeposit);
        this.movementUseCase.saveMovement("deposit", "send amount", amount, accountToDeposit);
        return this.mapAccountDeposit(accountToDeposit);
    }

    public AccountTransactionResponse withdrawInUserAccount(Long userId, Long accountId, Double amount) throws AccountTransactionException {
        Account accountToWithdraw = this.verifyAccountToTransaction(accountId, userId);
        if(accountToWithdraw == null) throw new AccountTransactionException("account to withdraw not found");
        boolean validAmountAllowed = amount <= accountToWithdraw.getAccountAmount();
        if(!validAmountAllowed) throw new AccountTransactionException(Messages.AMOUNT_INVALID_MESSAGE);
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

    public AccountTransactionResponse mapAccountDeposit(Account account) {
        AccountTransactionResponse accountTransactionResponse = new AccountTransactionResponse();
        accountTransactionResponse.setAccountId(account.getAccountId());
        accountTransactionResponse.setAmount(account.getAccountAmount());
        accountTransactionResponse.setUserId(account.getUser().getUserId());
        return accountTransactionResponse;
    }

    private AccountTransactionResponse mapAccountTransaction(Account account) {
        AccountTransactionResponse accountTransactionResponse = new AccountTransactionResponse();
        accountTransactionResponse.setAccountId(account.getAccountId());
        accountTransactionResponse.setAmount(account.getAccountAmount());
        accountTransactionResponse.setUserId(account.getUser().getUserId());
        accountTransactionResponse.setAccountType(account.getAccountType());
        return accountTransactionResponse;
    }
}
