package com.acme.banking.dbo.domain;


import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public final class Client {

    private final int id;
    private final String name;
    private final List<Account> accounts;

    public Client(int id, String name) {
        if (isNull(name) || name.isEmpty())
            throw new IllegalArgumentException("Parameter 'name' must not be null or empty.");

        accounts = new ArrayList<>();
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        requireNonNull(account, "Parameter 'account' must not be null.");
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return unmodifiableList(accounts);
    }
}