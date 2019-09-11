package com.acme.banking.dbo.error;

public class UniqueConstraintException extends Exception {
    public UniqueConstraintException(String message) {
        super(message);
    }
}
