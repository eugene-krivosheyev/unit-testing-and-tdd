package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private final static String ARG_EXCEPTION_MESSAGE_ID_NEGATIVE = "ID is expected to be positive int";
    private final static String ARG_EXCEPTION_MESSAGE_NAME_NULL = "Name is NULL";
    private final static String ARG_EXCEPTION_MESSAGE_NAME_EMPTY = "Name is empty";

    private final int id;
    private final String name;
    private final Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id < 0) throw new IllegalArgumentException(ARG_EXCEPTION_MESSAGE_ID_NEGATIVE);
        if (name == null) throw new IllegalArgumentException(ARG_EXCEPTION_MESSAGE_NAME_NULL);
        if (name.isEmpty()) throw new IllegalArgumentException(ARG_EXCEPTION_MESSAGE_NAME_EMPTY);

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
