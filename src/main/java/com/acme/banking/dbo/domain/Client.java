package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private final UUID id;
    private final String name;
    private Collection<Account> accounts;

    public Client(UUID id, String name, Collection<Account> accounts) {
        if (id == null) throw new IllegalArgumentException("ID is null");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Invalid name");
        if (accounts == null) throw new IllegalArgumentException("Accounts is null");
        if (accounts.isEmpty()) throw new IllegalArgumentException("Accounts is empty");

        this.accounts = accounts;
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }
}
