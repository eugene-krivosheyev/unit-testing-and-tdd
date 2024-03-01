package com.acme.banking.dbo.exceptions;

public class CreateClientException extends RuntimeException{

    public CreateClientException(String errorMessage){
        super(errorMessage);
    }
}
