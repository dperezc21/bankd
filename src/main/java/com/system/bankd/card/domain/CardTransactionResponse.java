package com.system.bankd.card.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardTransactionResponse {
    private Long accountId;
    private String cardType;
    private Long cardId;
}
