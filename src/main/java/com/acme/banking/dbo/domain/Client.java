package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(UUID id, String name) {
        if((name=="")) {throw new IllegalArgumentException();}
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
        return accounts;
    }

    public void addAccount (Account account){
        account.setClient(new Client(id, name));
        accounts.add(account);
    }

    public void removeAccount (Account account){
        accounts.remove(account);
    }
}
