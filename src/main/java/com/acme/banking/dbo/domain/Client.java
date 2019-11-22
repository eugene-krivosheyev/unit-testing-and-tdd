package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Client implements Account{
    private UUID id;
    private String name;
    private Collection<SavingAccount> savingAccounts = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (name == null || "".equals(name)) throw new IllegalArgumentException("name is null or empty");

        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public UUID getClientId() {
        return getId();
    }

    public String getName() {
        return name;
    }

    public Collection<SavingAccount> getAccounts() {
        return Collections.unmodifiableCollection(savingAccounts);
    }

    public void addIdToClientAccountIds(SavingAccount account) {
        if(account.getClient().getId() != this.getId()) throw new IllegalArgumentException("cannot add account to client because it is wrong");
            this.savingAccounts.add(account);
    }
}
