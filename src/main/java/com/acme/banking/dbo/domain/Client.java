package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private long id;
    private String name;
    private Collection<Account> accountIds = new ArrayList<>(); //TODO

    public Client(long id, String name) {
        if (id < 0) throw new IllegalArgumentException("id is less than zero");
        if (name ==null || "".equals(name)) throw new IllegalArgumentException("name is empty");

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
