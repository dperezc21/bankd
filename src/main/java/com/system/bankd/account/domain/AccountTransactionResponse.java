package com.system.bankd.account.domain;

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
