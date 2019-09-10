package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("name is null or empty");
        this.id = id;
        this.name = name;
    }

    public Collection<Account> getAccounts(){
        return accounts;
    }

    public void addAccount(Account account){
        accounts.add(account);
        account.setClient(this);
    }

    public void removeAccount(Account account){
        accounts.remove(account);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
