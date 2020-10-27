package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Client {
    private final UUID id;
    private final String name;
    private List<Account> accounts = new ArrayList<>();

    public Client(UUID id, String name, List<Account> accounts) {
        if (id == null) throw new IllegalArgumentException("id can't be null");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name can't be null or blank");
        if (accounts == null) throw new IllegalArgumentException("accounts can't be null");
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
