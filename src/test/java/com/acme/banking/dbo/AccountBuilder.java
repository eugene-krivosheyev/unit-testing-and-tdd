package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.UUID;

public class AccountBuilder {

    private UUID clientId = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c3");
    private UUID accountId = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private Client client;
    private double amount = .0;

    static AccountBuilder builder() {
        return new AccountBuilder();
    }

    AccountBuilder withId(UUID id) {
        accountId = id;
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
        return new SavingAccount(accountId, client, amount);
    }
}
