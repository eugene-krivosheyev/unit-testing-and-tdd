package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (Integer.signum(id) == -1) throw new IllegalArgumentException("id is negative");
        if (name == null || name.isEmpty()) throw  new IllegalArgumentException("name is empty");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(SavingAccount account) {
        if (account == null) throw  new IllegalArgumentException("account is null");

        this.accounts.add(account);
    }

     public Collection<Account> getAccounts() {
        return accounts;
     }
}
