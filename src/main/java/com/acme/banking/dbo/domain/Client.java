package com.acme.banking.dbo.domain;


import java.util.*;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accountIds = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        Objects.equals("", name);
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
        return Collections.unmodifiableCollection(accountIds);
    }
}
