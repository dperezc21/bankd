package com.system.bankd.infrastructure.implementations;

import com.system.bankd.domain.models.Movement;
import com.system.bankd.domain.repositories.AccountMovementRepository;
import com.system.bankd.infrastructure.database.MySqlAccountMovement;
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
