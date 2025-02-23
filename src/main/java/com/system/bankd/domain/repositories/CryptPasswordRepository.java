package com.system.bankd.domain.repositories;

public interface CryptPasswordRepository {
    String encryptPassword(String password);
    Boolean verifyPassword(String password, String passwordEncrypted);
}
