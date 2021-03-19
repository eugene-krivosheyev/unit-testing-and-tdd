package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IllegalFormatException;
import java.util.UUID;
import java.util.regex.Pattern;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null ) throw new IllegalArgumentException("id is null");
        else if (id.toString().length() != 36) throw new IllegalArgumentException("Invalid UUID string");

        if (name == null ) throw new IllegalArgumentException("name is null");
        else if (!Pattern.compile("^[A-Z][a-z]+$").matcher(name).find())
            throw new IllegalArgumentException("Wrong format name");

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
