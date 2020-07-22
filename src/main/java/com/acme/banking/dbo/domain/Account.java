package com.acme.banking.dbo.domain;

public interface Account {
    long getId();
    long getClientId(); //TODO reference integrity

    public void sendMoney(double amount);
    public void getMoney(double amount);

    double getAmount();
    //public double Balance(double amount);

}
