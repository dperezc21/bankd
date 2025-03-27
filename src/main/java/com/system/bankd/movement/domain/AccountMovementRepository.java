package com.system.bankd.movement.domain;

import java.util.List;

public interface AccountMovementRepository {
    List<Movement> getMomentsByAccountId(Long accountId);
    void saveMomentOfAccount(Movement movement);
}
