package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("Id should be grater than zero!");
        if (name == null) throw new IllegalArgumentException("Name shouldn't be null!");
        if (name.isEmpty()) throw new IllegalArgumentException("Name shouldn't be empty!");

        this.id = id;
        this.name = name;
    }

    public void addAccount (Account account) {
        if(account.getClient() != this) {
            throw new IllegalArgumentException("Account should have correct client!");
        }
        accounts.add(account);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }
}
