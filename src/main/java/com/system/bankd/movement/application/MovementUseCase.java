package com.system.bankd.movement.application;

import com.system.bankd.account.domain.Messages;
import com.system.bankd.account.domain.exceptions.AccountNotFoundException;
import com.system.bankd.account.domain.Account;
import com.system.bankd.movement.domain.Movement;
import com.system.bankd.movement.domain.AccountMovementRepository;
import com.system.bankd.account.domain.UserAccountRepository;
import com.system.bankd.movement.domain.MovementResponse;
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

    public List<MovementResponse> getAllMovements(Long accountId) throws AccountNotFoundException {
        Account findAccount = this.userAccountRepository.getUserAccountById(accountId);
        if(findAccount == null) throw new AccountNotFoundException(Messages.ACCOUNT_NOT_FOUND_MESSAGE);
        return this.accountMovementRepository.getMomentsByAccountId(accountId).stream().map(this::mapMovement).toList();
    }

    public MovementResponse mapMovement(Movement movement) {
        MovementResponse movementResponse = new MovementResponse();
        movementResponse.setId(movement.getMovementId());
        movementResponse.setName(movement.getMovementName());
        movementResponse.setDescription(movement.getDescription());
        movementResponse.setAmount(movement.getAmount());
        movementResponse.setCreatedDate(movement.getCreatedAt());
        return movementResponse;
    }
}
