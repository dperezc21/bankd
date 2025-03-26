package com.system.bankd.application;

import com.system.bankd.domain.constants.Messages;
import com.system.bankd.domain.exceptions.AccountNotFoundException;
import com.system.bankd.domain.exceptions.AccountTransactionException;
import com.system.bankd.domain.models.Account;
import com.system.bankd.domain.repositories.UserAccountRepository;
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
