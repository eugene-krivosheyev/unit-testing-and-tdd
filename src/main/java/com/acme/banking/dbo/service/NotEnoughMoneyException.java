package com.acme.banking.dbo.service;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String s) {
        super(s);
    }
}
