package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id < 0) throw new IllegalArgumentException("id!");
        if (name == null) throw new IllegalArgumentException("name!");
        if (name.isEmpty()) throw new IllegalArgumentException("name!");

        this.id = id;
        this.name = name;

        //==========

        if (id >= 0) {
            if (name != null && !name.isEmpty()) {
                this.id = id;
                this.name = name;
            } else {
                throw new IllegalArgumentException("name!");
            }
        } else {
            throw new IllegalArgumentException("id!");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
