package com.acme.banking.dbo.builder;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.UUID;

public class TestEntities {

    private final Client client;
    private final Account account;
    private final UUID id;
    private final String name;
    private final double amount;

    private TestEntities(Builder builder) {
        id = builder.id;
        name = builder.name;
        amount = builder.amount;
        client = new Client(builder.id, builder.name);
        account = new SavingAccount(builder.id, builder.amount);
    }

    public Client getClient() {
        return client;
    }

    public Account getAccount() {
        return account;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public static class Builder {
        private String name = "Stub name";
        private UUID id = UUID.randomUUID();
        private double amount = .1;

        public Builder() {}

        public TestEntities build() {
            return new TestEntities(this);
        }
    }
}
