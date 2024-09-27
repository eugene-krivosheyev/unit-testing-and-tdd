package com.acme.banking.dbo.exception;

public class IdValidationException extends RuntimeException{

    public static final String DEFAULT_MESSAGE = "ID не может быть отрицательным числом";

    public IdValidationException() {
        super(DEFAULT_MESSAGE);
    }
}
