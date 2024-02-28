package com.acme.banking.dbo.exception;

public class NameValidationException extends RuntimeException{
    public static final String DEFAULT_MESSAGE =
        "Name validation error. Make sure name doesn't start or end with whitespace or hyphen and contains one letter.";
    public NameValidationException() {
        super(DEFAULT_MESSAGE);
    }
}
