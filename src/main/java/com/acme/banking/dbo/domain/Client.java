package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private long id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(Long id, String name) {
        if (id == null || id < 0) throw new IllegalArgumentException("Incorrect id!");
        if (name == null || name.equals("") || name.equals("Bill Gates"))
            throw new IllegalArgumentException("Incorrect name!");

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
