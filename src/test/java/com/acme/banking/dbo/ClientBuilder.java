package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import java.util.UUID;


public class ClientBuilder {
    private UUID id;
    private String name;
    private Collection<Account> accounts;

    static ClientBuilder builder() {
        return new ClientBuilder();
    }

    ClientBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    ClientBuilder withAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    Client build() {
        return new Client(id, name, accounts);
    }
}
