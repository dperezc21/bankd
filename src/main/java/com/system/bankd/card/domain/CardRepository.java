package com.system.bankd.card.domain;

import java.util.List;

public interface CardRepository {
    List<Card> cardsByAccount(Long accountId);
    void saveCardUserAccount(Card card);
}
