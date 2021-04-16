package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;


/**
 * SOLID :
 * SRP + OCP + LSP + ISP + DIP
 * => internal quality model
 */
public class Processing {
    private AccountRepository accounts;
    private Cash cash;
//    private Cash cash = new Cash(); // Creator [GRASP]
//    private Cash cash = ConfigurableCashFactory.create(); // Factory Method [GoF]
//    private Cash cash = TestConfigurableCashFactory.create(); // Abstract Factory [GoF]

    //DI: constructor (+field, setter, method)
    public Processing(AccountRepository accounts, Cash cash) {
        this.accounts = accounts;
        this.cash = cash;
    }

//    @Endpoint DI: method injection
    public Client createClient(/*@RequsetBody @Validate*/ String name) {
        return null; //TODO
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return null; //TODO
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        if (amount <= 0) throw new IllegalArgumentException();

        Account from = accounts.findById(fromAccountId);
        Account to = accounts.findById(toAccountId);

//        from.setAmount(from.getAmount() - amount);
//        to.setAmount(to.getAmount() + amount);

//        from.withdraw(amount);
//        to.deposit(amount);

//        Account updatedFrom = from.withdraw(amount);
//        Account updatedTo = from.deposit(amount);

        accounts.save(from);
        accounts.save(to);
    }

    public void cash(double amount, int fromAccountId) {
        cash.log(amount, fromAccountId);
        log();
    }

    /**
     *  Public API, Contract, Interface
     *  ==== vs =====
     *  Implementation -> хрупкость тестов
     */
    // FEST Reflect
    private int state;
    private void log() {

    }
}
