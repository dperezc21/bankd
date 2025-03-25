package com.system.bankd.infrastructure.database;

import com.system.bankd.domain.models.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MySqlCard extends CrudRepository<Card, Long> {
    @Query("select c from Card c where c.account.user.userId = ?1")
    List<Card> getCardsByAccount(Long accountId);
}
