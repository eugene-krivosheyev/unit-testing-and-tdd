package com.acme.banking.dbo.exceptions;

public class GetAccountsException extends RuntimeException{
    public GetAccountsException(String message){
        super(message);
    }
}
