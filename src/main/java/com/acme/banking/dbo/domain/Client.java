package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {

        if (id <= 0) throw new IllegalArgumentException("Invalid value for id!");
        if ("".equals(name) || name == null) throw new IllegalArgumentException("Client name cannot be empty");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts(){
        return accounts;
    }

    public void addAccount(Account account){
        if (account.getClient().getName().equals(name)){
            accounts.add(account);
        } else {
            throw new IllegalStateException("This account does not belong " + name);
        }

    }

}
