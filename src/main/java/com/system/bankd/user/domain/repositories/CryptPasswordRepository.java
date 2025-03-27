package com.system.bankd.user.domain.repositories;

public interface CryptPasswordRepository {
    String encryptPassword(String password);
    Boolean verifyPassword(String password, String passwordEncrypted);
}
