package com.system.bankd.application;

import com.system.bankd.domain.repositories.CryptPasswordRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class BCryptPassword implements CryptPasswordRepository {

    private final Integer STRENGTH_PASSWORD_NUMBER = 16;

    @Override
    public String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(this.STRENGTH_PASSWORD_NUMBER);
        return encoder.encode(password);
    }
}
