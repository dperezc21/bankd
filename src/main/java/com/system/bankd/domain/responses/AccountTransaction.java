package com.system.bankd.domain.responses;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountTransaction {
    private Long userId;
    private Double amount;
    private Long accountId;
}
