package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Client {
    private final int id;
    private final String name;
    private final Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 0) throw new IllegalArgumentException(String.format("Incorrect client Id (id=%s)", id));
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException(String.format("Incorrect client Name (id=%s)", id));
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        if (account == null) throw new IllegalStateException("Account is null");
        if (!account.getClient().equals(this)) throw new IllegalStateException("Account belongs another client");
        accounts.add(account);
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableCollection(accounts);
    }
}
