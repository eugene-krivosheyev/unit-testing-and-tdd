package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accountIds = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (name == null) throw new IllegalArgumentException();
        if (id == null) throw new IllegalArgumentException();

        this.id = id;
        this.name = name;
    }

    public void addAccount(Account account) {
        accountIds.add(account);
    }

    public void removeAccount(Account account) {
        accountIds.remove(account);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccountIds() { return  accountIds; }
}
