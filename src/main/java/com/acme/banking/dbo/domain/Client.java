package com.acme.banking.dbo.domain;

import java.util.UUID;

public class Client {
    private UUID id;
    private String name;

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("id = null");
        if (name == null || "".equals(name)) throw new IllegalArgumentException("name = null or empty");

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
