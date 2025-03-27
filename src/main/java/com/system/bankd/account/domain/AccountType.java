package com.system.bankd.account.domain;

import lombok.Getter;

@Getter
public enum AccountType {
    SAVING("saving"),
    CURRENT("current");

    private String value;

    AccountType(String value) {
        this.value = value;
    }
}
