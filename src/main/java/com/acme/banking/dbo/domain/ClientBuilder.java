package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ClientBuilder {

    private UUID id = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private String name = "dummy client name";
    private List<Account> accounts = Collections.emptyList();


    public ClientBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    public Client build() {
        return new Client(id, name, accounts);
    }
}
