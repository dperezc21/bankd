package com.system.bankd.application;

import com.system.bankd.domain.exceptions.AccountNotFoundException;
import com.system.bankd.domain.models.Account;
import com.system.bankd.domain.models.Movement;
import com.system.bankd.domain.repositories.AccountMovementRepository;
import com.system.bankd.domain.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementUseCase {

    @Autowired private AccountMovementRepository accountMovementRepository;
    @Autowired private UserAccountRepository userAccountRepository;

    public void saveMovement(String name, String description, Double amount, Account account) {
        Movement movement = new Movement();
        movement.setMovementName(name);
        movement.setDescription(description);
        movement.setAmount(amount);
        movement.setAccount(account);
        accountMovementRepository.saveMomentOfAccount(movement);
    }

    public List<Movement> getAllMovements(Long accountId) throws AccountNotFoundException {
        Account findAccount = this.userAccountRepository.getUserAccountById(accountId);
        if(findAccount == null) throw new AccountNotFoundException("account not found");
        return this.accountMovementRepository.getMomentsByAccountId(accountId);
    }

}
