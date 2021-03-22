package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("id should not be null");
        if (name==null) throw new IllegalArgumentException("name should not be null");
        if (name.isEmpty()) throw new IllegalArgumentException("name should not be empty");
        if (name.trim().isEmpty()) throw new IllegalArgumentException("name should not be only spaces");
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
