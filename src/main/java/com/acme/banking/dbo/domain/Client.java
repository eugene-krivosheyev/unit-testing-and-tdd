package com.acme.banking.dbo.domain;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class Client {

    private final int id;
    private final String name;
    private final Set<Account> accounts;

    public Client(int id, String name) {
        if (isNull(name) || name.isEmpty())
            throw new IllegalArgumentException("Parameter 'name' must not be null or empty.");

        accounts = new HashSet<>();
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

        boolean isSaved = accounts.add(account);
        if (isSaved) {
            account.setClient(this);
        }
    }

    public void removeAccount(Account account) {
        if (isNull(account)) return;

        boolean isRemoved = accounts.remove(account);
        if (isRemoved) account.removeClient();
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }
}