package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("id cannot be a null");
        if (name == null) throw new IllegalArgumentException("name cannot be an empty");
        if (name.isEmpty()) throw new IllegalArgumentException("name cannot be a null");
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        if (!account.getClientId().equals(id)) {
            throw new IllegalArgumentException("clint id is not equal current client id");
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
