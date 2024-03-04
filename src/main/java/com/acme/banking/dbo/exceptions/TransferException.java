package com.acme.banking.dbo.exceptions;

public class TransferException extends RuntimeException{
    public TransferException(String message){
        super(message);
    }
}
