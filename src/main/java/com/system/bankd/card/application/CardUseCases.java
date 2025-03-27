package com.system.bankd.card.application;

import com.system.bankd.account.domain.Messages;
import com.system.bankd.account.domain.exceptions.AccountNotFoundException;
import com.system.bankd.account.domain.exceptions.AccountTransactionException;
import com.system.bankd.account.domain.Account;
import com.system.bankd.account.domain.UserAccountRepository;
import com.system.bankd.movement.application.MovementUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardUseCases {

    @Autowired private UserAccountRepository userAccountRepository;
    @Autowired private MovementUseCase movementUseCase;

    public Double takeOutUsingCard(Long accountId, Double amount) throws AccountNotFoundException, AccountTransactionException {
        Account getAccount = this.userAccountRepository.getUserAccountById(accountId);
        if(getAccount == null) throw new AccountNotFoundException(Messages.ACCOUNT_NOT_FOUND_MESSAGE);
        boolean transactionValid = getAccount.getAccountAmount() > amount;
        if(!transactionValid) throw new AccountTransactionException(Messages.AMOUNT_INVALID_MESSAGE);
        getAccount.setAccountAmount(getAccount.getAccountAmount() - amount);
        this.movementUseCase.saveMovement("", "", getAccount.getAccountAmount()- amount, getAccount);
        return amount;
    }
}
