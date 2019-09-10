package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;

    private Collection<Account> accounts = new ArrayList<>();

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        if (name == null || name.equals("")) throw new IllegalArgumentException("name cannot be null or empty");
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
        return Collections.unmodifiableCollection(accounts);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }
}
