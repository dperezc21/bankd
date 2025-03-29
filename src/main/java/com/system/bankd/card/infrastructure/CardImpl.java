package com.system.bankd.card.infrastructure;

import com.system.bankd.card.domain.Card;
import com.system.bankd.card.domain.CardRepository;
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

    @Override
    public void saveCardUserAccount(Card card) {
        this.mySqlCard.save(card);
    }
}
