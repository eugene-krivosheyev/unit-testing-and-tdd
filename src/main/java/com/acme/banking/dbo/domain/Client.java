package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(UUID id, String name, Collection<? extends Account> accounts) {
        if (id == null) throw new IllegalArgumentException("id");
        if (name == null) throw new IllegalArgumentException("name");
        if (accounts == null) throw new IllegalArgumentException("accounts");

        this.id = id;
        this.name = name;
        this.accounts = new ArrayList<>(accounts);
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
