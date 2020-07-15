package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accountIds = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (name == null || name.equals("") || name.equals(" ")) throw new IllegalArgumentException();
        if (id < 0) throw new IllegalArgumentException();
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
