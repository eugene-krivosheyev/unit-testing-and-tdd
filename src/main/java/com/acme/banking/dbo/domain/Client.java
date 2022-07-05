package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;

    public Collection<Account> getAccounts() {
        return accounts;
    }

    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if(id == 0) throw new IllegalArgumentException();
        if(name.isEmpty()) throw new IllegalArgumentException();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        if (account == null) throw new IllegalArgumentException();
        this.accounts.add(account);
        account.setClient(this);
    }
}
