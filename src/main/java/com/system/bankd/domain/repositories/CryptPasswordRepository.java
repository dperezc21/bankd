package com.system.bankd.domain.repositories;

public interface CryptPasswordRepository {
    String encryptPassword(String password);
}
