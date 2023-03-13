package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

import static com.acme.banking.dbo.util.Utils.require;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        require(id > 0);
        require(name != null && !name.isEmpty());
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
