package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final int id;
    private final String name;
    private final List<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id < 0) throw new IllegalArgumentException("id < 0");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Bad name");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        if (account.getClient() != this) throw new IllegalArgumentException("Incorrect client! FRAUD!");
        this.accounts.add(account);
    }
}
