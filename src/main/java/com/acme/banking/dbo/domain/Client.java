package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts;

    public Client(UUID id, String name, Collection<Account> accounts) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (name == null) throw new IllegalArgumentException("name is null");
        if ("".equals(name)) throw new IllegalArgumentException("name is empty");
        if (accounts == null) throw new IllegalArgumentException("accounts is null");

        this.id = id;
        this.name = name;
        this.accounts = accounts;
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
