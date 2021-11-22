package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public class Processing {

    private AccountRepository accounts;
    private Cash cachemachine;

    // This can't be overriden by DI
//    private AccountRepository accounts = new AccountRepository();
    // How should we implement Processing, so we can replace real processing
    // with a mock one

    /*
     *  Dependency INJECTION
     */
    public Processing(AccountRepository accounts, Cash cachemachine) {
        this.accounts = accounts;
        this.cachemachine = cachemachine;
    }

    public Client createClient(String name) {
        return null; //TODO
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        ///...
        return accounts.getAccountsByClientId(clientId);
        ///...
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        //TODO

        Account from = accounts.getAccountById(fromAccountId);
        Account to = accounts.getAccountById(toAccountId);

        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);

        accounts.save(from);
        accounts.save(to);
    }

    public void cash(double amount, int fromAccountId) {
        //Cash.log(amount, fromAccountId) // refactoring
        cachemachine.log(amount, fromAccountId);
    }
}
