package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private Long id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(Long id, String name) {
        if( id == null || id < 0) throw new IllegalArgumentException("Wrong id");
        if( name == null || name.equals("")) throw new IllegalArgumentException("Wrong name");

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
