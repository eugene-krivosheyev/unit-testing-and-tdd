package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<UUID> accountIds = new ArrayList<>(); //TODO

    public Client(UUID id, String name, Collection<UUID> accountIds) {
        if (id == null) throw new IllegalArgumentException("id must not null");
        if (name == null || "".equals(name)) throw new IllegalArgumentException("name must not null and not empty");
        if (accountIds == null) throw new IllegalArgumentException("accounts must not null");
        this.id = id;
        this.name = name;
        this.accountIds = accountIds;

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<UUID> getAccountIds() {
        return accountIds;
    }
}