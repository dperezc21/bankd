package com.system.bankd.application;

import java.util.Date;

public class GenerateId {

    public static String generateId() {
        return new Date().getTime() + "";
    }
}
