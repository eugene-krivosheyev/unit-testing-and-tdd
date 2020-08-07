package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;

public class Core {
    private TxManager txManager;

    /**
     * Constructor Dependency Injection:
     * @param txManager
     */
    public Core(TxManager txManager) {
        this.txManager = txManager;
    }


    public void transfer(Account accountFrom, Account accountTo, double amount) {
        try {
            txManager.start();
            accountFrom.withdraw(amount);
            accountTo.deposit(amount);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
            //TODO throw e;
        }
    }
}
