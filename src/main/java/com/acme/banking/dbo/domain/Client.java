package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private final int id;
    private final String name;
    private final Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("Id shouldn't be 0 or negative");
        if (name == null) throw new IllegalArgumentException("Name shouldn't be null");
        if (name.isEmpty()) throw new IllegalArgumentException("Name shouldn't be empty");
        if (name.isBlank()) throw new IllegalArgumentException("Name shouldn't be blank");

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
}
