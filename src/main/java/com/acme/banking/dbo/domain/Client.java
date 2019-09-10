package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (name == null) throw new IllegalArgumentException("name is null");
        if (name.isEmpty()) throw new IllegalArgumentException("name is empty");
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        if (!account.getClientId().equals(id)) {
            throw new IllegalArgumentException("This account does not belong to the client");
        }
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }
}
