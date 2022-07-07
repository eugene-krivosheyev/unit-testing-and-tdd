package com.acme.banking.dbo.domain;


import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("id is not positive");
        }
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("name hasn't any text");
        }
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }
}
