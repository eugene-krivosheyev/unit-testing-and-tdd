package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Client {
    private final int id;
    private final String name;
    private final Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 0 ) {
            throw new IllegalArgumentException("id should not be less than 0");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name should not be null or empty");
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
        return Collections.unmodifiableCollection(accounts);
    }

    public void saveAccountForClient(Account account) {
        if (!this.equals(account.getClient())) {
            throw new IllegalArgumentException("The account belongs to another client");
        }
        accounts.add(account);
    }
}
