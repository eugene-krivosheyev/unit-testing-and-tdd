package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private final Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 0 || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
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
}
