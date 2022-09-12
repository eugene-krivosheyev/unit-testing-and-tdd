package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 1) throw new IllegalArgumentException("Некорректный ID");
        if (name.isEmpty()) throw new IllegalArgumentException("Пустой Name");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }
}
