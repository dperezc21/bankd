package com.system.bankd.card.application;

import com.system.bankd.account.application.AccountUtils;
import com.system.bankd.account.domain.Account;
import com.system.bankd.account.domain.Messages;
import com.system.bankd.account.domain.exceptions.AccountNotFoundException;
import com.system.bankd.account.domain.exceptions.AccountTransactionException;
import com.system.bankd.card.domain.Card;
import com.system.bankd.card.domain.CardRepository;
import com.system.bankd.movement.application.MovementUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardUseCases {

    @Autowired private MovementUseCase movementUseCase;
    @Autowired private CardRepository cardRepository;

    public Double takeOutUsingCard(Long accountId, Double amount) throws AccountNotFoundException, AccountTransactionException {
        Account getAccount = AccountUtils.getUserAccount(accountId);
        boolean transactionValid = getAccount.getAccountAmount() > amount;
        if(!transactionValid) throw new AccountTransactionException(Messages.AMOUNT_INVALID_MESSAGE);
        getAccount.setAccountAmount(getAccount.getAccountAmount() - amount);
        this.movementUseCase.saveMovement("", "", getAccount.getAccountAmount()- amount, getAccount);
        return amount;
    }

    public void saveCardUserAccount(Long accountId, String cardType) throws AccountNotFoundException {
        Account account = AccountUtils.getUserAccount(accountId);
        Card card = new Card(cardType, account);
        this.cardRepository.saveCardUserAccount(card);
    }
}
