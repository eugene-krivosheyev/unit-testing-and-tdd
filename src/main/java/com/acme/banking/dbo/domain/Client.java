package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<UUID> accountIds = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("Id should be not null");
        if (name == null) throw new IllegalArgumentException("Name should be not null");

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
        if (account.getClientId() != id)
            throw new IllegalStateException("Account does not belong to this client");
        accountIds.add(account.getId());
    }

    public Collection<UUID> getAccountIds() {
        return this.accountIds;
    }

}
