package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {

        if (id < 0) throw new IllegalArgumentException("id should be more zero");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name should be not empty");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(SavingAccount account){

        if (account == null) throw new IllegalArgumentException("Account is null");
        if (id != account.getClient().getId()) throw new IllegalStateException("Account related to another client");

        accounts.add(account);
    }

    public int getAccountsSize() {
        return accounts.size();
    }

    public Optional<Account> getAccount(int accountId){
        return accounts.stream().filter(a -> a.getId() == accountId).findAny();
    }
}
