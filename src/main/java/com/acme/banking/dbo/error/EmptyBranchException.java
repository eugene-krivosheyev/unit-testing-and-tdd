package com.acme.banking.dbo.error;

public class EmptyBranchException extends Exception {
    public EmptyBranchException(String message) {
        super(message);
    }
}
