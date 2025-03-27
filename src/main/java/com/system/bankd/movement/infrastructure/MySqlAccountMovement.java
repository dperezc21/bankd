package com.system.bankd.movement.infrastructure;

import com.system.bankd.movement.domain.Movement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MySqlAccountMovement extends CrudRepository<Movement, Long> {

    @Query("select m from Movement m where m.account.accountId = ?1")
    List<Movement> getMovementsOfAccount(Long accountId);
}
