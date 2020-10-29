package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.UUID;

public class AccountBuilder {
    private UUID id;
    private Client client;
    private double amount;

    static AccountBuilder builder() {
        return new AccountBuilder();
    }

    AccountBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    AccountBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    AccountBuilder withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    Account build() {
        return new SavingAccount(id, client, amount);
    }
}
