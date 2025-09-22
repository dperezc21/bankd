package com.system.bankd.account.domain;

import com.system.bankd.card.domain.Card;
import com.system.bankd.card.domain.CardTransactionResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountTransactionResponse {
    private Long userId;
    private Double amount;
    private Long accountId;
    private String accountType;
}

