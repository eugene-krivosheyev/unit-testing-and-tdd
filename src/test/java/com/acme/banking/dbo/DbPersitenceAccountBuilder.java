package com.acme.banking.dbo;


import com.acme.banking.dbo.domain.Client;

import java.util.UUID;

public class DbPersitenceAccountBuilder {
    private int id;
    private String name;

    public DbPersitenceAccountBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public DbPersitenceAccountBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Client build() {
        return new Client(UUID.fromString(Integer.toString(id)), name);
    }
}
