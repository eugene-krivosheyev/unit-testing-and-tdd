package com.acme.banking.dbo.error;

public class EmptyClientException extends Exception {
    public EmptyClientException(String message) {
        super(message);
    }
}
