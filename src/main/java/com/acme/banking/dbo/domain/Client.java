package com.acme.banking.dbo.domain;

import java.util.List;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private List<Account> accounts;

    public Client(UUID id, String name, List<Account> accounts) {
        if (id == null) throw new IllegalArgumentException("id is Null");
        if (name == null) throw new IllegalArgumentException("name is Null");
        if (name.isEmpty()) throw new IllegalArgumentException("name is Empty");
        if (accounts == null) throw new IllegalArgumentException("accounts is Null");

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

    public List<Account> getAccounts() {
        return accounts;
    }
}
