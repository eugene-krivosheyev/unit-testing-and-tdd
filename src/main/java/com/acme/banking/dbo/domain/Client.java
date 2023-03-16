package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {

    private int id;
    private String name;
    private Collection<Account> accounts;

    public Client(int id, String name) {

        if (id < 0) {
            throw new IllegalArgumentException(String.format("Client id is not valid. %d", id));
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(String.format("Client name is not valid %s", name));
        }

        this.id = id;
        this.name = name;
        this.accounts = new ArrayList<>();

    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account is not valid");
        }
        if (!account.getClient().equals(this)) {
            throw new IllegalArgumentException("Client is not valid");
        }
        this.accounts.add(account);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

}
