package com.system.bankd.application;

import com.system.bankd.domain.models.Account;
import com.system.bankd.domain.models.Movement;
import com.system.bankd.domain.repositories.AccountMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementUseCase {

    @Autowired private AccountMovementRepository accountMovementRepository;

    public void saveMovement(String name, String description, Double amount, Account account) {
        Movement movement = new Movement();
        movement.setMovementName(name);
        movement.setDescription(description);
        movement.setAmount(amount);
        movement.setAccount(account);
        accountMovementRepository.saveMomentOfAccount(movement);
    }

}
