package com.acme.banking.dbo.domain;

import java.math.BigDecimal;

public class CashTransaction {
    private double amount;
    private Integer fromAccountId;

    public double getAmount() {
        return amount;
    }

    public CashTransaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Integer getFromAccountId() {
        return fromAccountId;
    }

    public CashTransaction setFromAccountId(Integer fromAccountId) {
        this.fromAccountId = fromAccountId;
        return this;
    }
}
