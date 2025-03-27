package com.system.bankd.account.application;

import java.util.Date;

public class GenerateId {

    public static String generateId() {
        return new Date().getTime() + "";
    }
}
