package com.system.bankd.domain.repositories;

import com.system.bankd.domain.models.Card;

import java.util.List;

public interface CardRepository {
    List<Card> cardsByAccount(Long accountId);
}
