package com.system.bankd.card.domain;

import lombok.Getter;

@Getter
public enum CardType {
    BASIC("basic"),
    CREDIT("credit"),
    EXPENSIVE("expensive");

    private String value;

    CardType(String value) {
        this.value = value;
    }
}
