package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private final int id;
    private final String name;
    private final Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 0) throw new IllegalArgumentException(String.format("Incorrect client Id (id=%s)", id));
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException(String.format("Incorrect client Name (id=%s)", id));
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
