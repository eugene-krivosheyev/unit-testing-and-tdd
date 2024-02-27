package com.acme.banking.dbo.domain;

import static com.acme.banking.dbo.domain.Errors.CLIENT_EMPTY_NAME_MESSAGE;
import static com.acme.banking.dbo.domain.Errors.CLIENT_NEGATIVE_ID_MESSAGE;
import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id < 0) {
            throw new IllegalArgumentException(CLIENT_NEGATIVE_ID_MESSAGE);
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(CLIENT_EMPTY_NAME_MESSAGE);
        }
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
