package com.acme.banking.dbo.errors;

public class UniqueConstraintException extends Exception {
    public UniqueConstraintException(String message) {
        super(message);
    }
}
