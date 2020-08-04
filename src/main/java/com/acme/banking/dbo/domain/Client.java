package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private Integer id;
    private String name;
    private Collection<Integer> accountIds = new ArrayList<Integer>(); //TODO

    public Client(Integer id, String name) {
        if (id == null) throw new IllegalArgumentException("Error: id is null");
        if (name == null) throw new IllegalArgumentException("Error: name is null");

        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
