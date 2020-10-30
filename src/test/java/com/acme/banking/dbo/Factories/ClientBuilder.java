package com.acme.banking.dbo.Factories;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class ClientBuilder {

    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    private UUID id = ID_STUB;
    private String name = "dummy";
    private List<Account> accounts = mock(List.class);

    public static ClientBuilder create()
    {
        return new ClientBuilder();
    }

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
