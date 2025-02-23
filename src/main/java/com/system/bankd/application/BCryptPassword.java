package com.system.bankd.application;

import com.system.bankd.domain.repositories.CryptPasswordRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class BCryptPassword implements CryptPasswordRepository {

    @Override
    public String encryptPassword(String password) {
        int STRENGTH_PASSWORD_NUMBER = 16;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGTH_PASSWORD_NUMBER);
        return encoder.encode(password);
    }

    @Override
    public Boolean verifyPassword(String password, String passwordEncrypted) {
        if(password == null) return false;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, passwordEncrypted);
    }
}
