package com.system.bankd.domain.responses;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDeposit {
    private Long userId;
    private Double amount;
    private Long accountId;
}
