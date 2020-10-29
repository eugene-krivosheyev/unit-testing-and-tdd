package com.acme.banking.dbo;


import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class ClientBuilder {
    private UUID id = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private String name = "dummy";
    private Collection<Account> accounts = Collections.EMPTY_LIST;

    public ClientBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    public Client build() {
        return new Client(id, name, accounts);
    }
}
