package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<UUID> accountIds = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (name == null || "".equals(name)) throw new IllegalArgumentException("name is null or empty");

        this.id = id;
        this.name = name;
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

    public void setAccountIds(Collection<UUID> accountIds) {
        this.accountIds = accountIds;
    }

    public void addIdToClientAccountIds(UUID accountId) {
        this.accountIds.add(accountId);
    }
}
