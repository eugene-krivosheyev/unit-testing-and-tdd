package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.errors.UniqueConstraintException;
import com.acme.banking.dbo.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private Collection<UUID> accountIds = new ArrayList<>();
    private ObjectUtils utils = new ObjectUtils();

    public Client(UUID id, String name) throws NullPointerException, IllegalArgumentException {
        this.id = utils.requireNonNull(id, "id must not be null or empty");
        this.name = utils.requireNonNull(name, "name must not be null or empty");
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) throws UniqueConstraintException {
        if (accountIds.contains(account.getId())) {
            throw new UniqueConstraintException("Account has exist");
        }
        accountIds.add(account.getId());
        account.setClient(this);
    }

    public void removeAccount(Account account) {
        account.removeClient();
        accountIds.remove(account.getId());
    }

    public boolean existAccount(Account account) {
        return accountIds.contains(account.getId());
    }

    public boolean hasAccounts() {
        return accountIds.isEmpty();
    }
}
