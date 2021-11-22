package com.acme.banking.dbo.domain;

public class Cash {

    public void log(double amount, int fromAccountId) {
        System.out.println(fromAccountId + " := " + amount);
    }
}
