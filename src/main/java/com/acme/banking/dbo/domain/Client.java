package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();
    private ObjectUtils utils = new ObjectUtils();

    public Client(UUID id, String name) {
        this.id = utils.requireNonNull(id, "id must not be null or empty");
        this.name = utils.requireNonNull(name, "name must not be null or empty");
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public boolean existAccount(Account account) {
        return accounts.contains(account);
    }

    public boolean hasAccounts() {
        return accounts.isEmpty();
    }
}
