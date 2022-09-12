package com.acme.banking.dbo.domain;

public class Cash {

    public Cash() {}

    public void log(double amount, int fromAccountId) {
        System.out.println(String.format("amount: %f, fromAccountId: %d", amount, fromAccountId));
    }
}
