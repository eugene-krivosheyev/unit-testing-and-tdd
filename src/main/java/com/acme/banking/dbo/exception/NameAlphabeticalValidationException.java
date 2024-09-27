package com.acme.banking.dbo.exception;

public class NameAlphabeticalValidationException extends RuntimeException{
    public static final String DEFAULT_MESSAGE = "Name содержит символы помимо кириллицы, латиницы, пробела и дефиса";
    public NameAlphabeticalValidationException() {
        super(DEFAULT_MESSAGE);
    }
}
