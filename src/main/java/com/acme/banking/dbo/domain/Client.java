package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if(id <= 0) throw new IllegalArgumentException("ID must be positive");
        if(name == null) throw new IllegalArgumentException("Name cannot be null.");
        if(name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        if(name.length() > 30) throw new IllegalArgumentException("Name length must be <= 30.");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
