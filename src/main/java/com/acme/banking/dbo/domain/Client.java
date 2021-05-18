package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 0) { throw new IllegalArgumentException("Client id should be positive!"); }
        if (name == null || name.isEmpty()) { throw new IllegalArgumentException("Client name should be not null and not empty!"); }

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
        return Collections.unmodifiableCollection(accounts);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
