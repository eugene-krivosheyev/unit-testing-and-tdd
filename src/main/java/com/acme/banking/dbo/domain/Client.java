package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        // Too complex, happy-fail pattern
        // (c) Fowler
//        if (name != null && !name.isEmpty()) { // Lazy AND
//            this.id = id;
//            this.name = name;
//        } else {
//            throw new IllegalArgumentException("Null!!11")
//        }

        // Guard clause
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Client should not be Null or Empty:" + name);
        if (id < 0 ) throw new IllegalArgumentException("Client id not be negative, id:" + id);

        this.id = id;
        this.name = name;
    }

    public void addAccount(Account clientAccount) {
        this.accounts.add(clientAccount);
    }

    public boolean checkAccountIsOwnedBy(Account possibleAccount) {
        return accounts.contains(possibleAccount);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        ///private Collection<Account> accounts = new ArrayList<>(); //TODO
        return accounts;
    }
}
