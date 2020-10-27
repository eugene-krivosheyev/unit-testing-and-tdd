package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null) throw  new IllegalArgumentException("Id is null");
        if (name == null) throw  new IllegalArgumentException("Name is null");
        if (name.isBlank()) throw  new IllegalArgumentException("Name is blank");

        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
