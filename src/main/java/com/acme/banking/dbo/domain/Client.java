package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import static java.util.Objects.isNull;


public class Client {
    private long id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO
    public Client(Long id, String name) {
        if (isNull(id) || id < 0) throw new IllegalArgumentException();
        if (isNull(name) || name.equals("")) throw new IllegalArgumentException();

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
