package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<UUID> accountIds = new ArrayList<>(); //TODO
    private List<SavingAccount> savingAccountList = new ArrayList<>();

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException();
        if (name == null || "".equals(name)) throw new IllegalArgumentException();

        this.id = id;
        this.name = name;
    }

    public Client(UUID id, String name, List<SavingAccount> savingAccountList) {
        if (id == null) throw new IllegalArgumentException("id = null");
        if (name == null || "".equals(name)) throw new IllegalArgumentException("name = null or empty");
        if (savingAccountList == null || savingAccountList.isEmpty()) throw new IllegalArgumentException("savingAccountList = null or empty");

        this.id = id;
        this.name = name;
        this.savingAccountList = savingAccountList;
    }

    public UUID getId() {
        return id;
    }

    public List<SavingAccount> getSavingAccountList() {
        return savingAccountList;
    }

    public String getName() {
        return name;
    }
}
