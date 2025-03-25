package com.system.bankd.infrastructure.implementations;

import com.system.bankd.domain.models.Card;
import com.system.bankd.domain.repositories.CardRepository;
import com.system.bankd.infrastructure.database.MySqlCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardImpl implements CardRepository {
    private @Autowired MySqlCard mySqlCard;

    @Override
    public List<Card> cardsByAccount(Long accountId) {
        return this.mySqlCard.getCardsByAccount(accountId).stream().toList();
    }
}
