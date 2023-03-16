package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private final int id;
    private final String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        if (name == null) {
            throw new IllegalArgumentException("Name must be not null");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must be not empty");
        }
        this.id = id;
        this.name = name;
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

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account must be not null");
        }
        if (!account.getClient().equals(this)) {
            throw new IllegalStateException("Can't add Account, because clients are different");
        }
        accounts.add(account);
    }
}
