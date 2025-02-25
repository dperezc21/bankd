package com.system.bankd.domain.repositories;

import com.system.bankd.domain.models.Movement;

import java.util.List;

public interface AccountMovementRepository {
    List<Movement> getMomentsByAccountId(Long accountId);
    void saveMomentOfAccount(Movement movement);
}
