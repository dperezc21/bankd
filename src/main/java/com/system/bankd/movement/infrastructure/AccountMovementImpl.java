package com.system.bankd.movement.infrastructure;

import com.system.bankd.movement.domain.Movement;
import com.system.bankd.movement.domain.AccountMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountMovementImpl implements AccountMovementRepository {

    @Autowired private MySqlAccountMovement mySqlAccountMovement;

    @Override
    public List<Movement> getMomentsByAccountId(Long accountId) {
        return this.mySqlAccountMovement.getMovementsOfAccount(accountId);
    }

    @Override
    public void saveMomentOfAccount(Movement movement) {
        this.mySqlAccountMovement.save(movement);
    }
}
