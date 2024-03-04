package com.acme.banking.dbo.exception;

public class AccountNotFoundException extends RuntimeException {

    private Integer idThatDoNotFound;
    public Integer getIdThatDoNotFound() {
        return idThatDoNotFound;
    }

    public AccountNotFoundException(int id) {
        this.idThatDoNotFound = id;
    }
}
