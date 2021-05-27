package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if ( id < 0)  throw new IllegalArgumentException();
        if ( name == null || name.isEmpty())  throw new IllegalArgumentException();

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean checkAccount(Account account){
        return this.accounts.contains(account);
    }

    public Collection<Account> getAccounts(){
        return Collections.unmodifiableCollection(this.accounts);
    }

    public void addAccount(Account newAccount){
        this.accounts.add(newAccount);
    }
}
