package com.system.bankd.domain.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateEntryException extends DataIntegrityViolationException {
    public DuplicateEntryException(String msg) {
        super(msg);
    }
}
