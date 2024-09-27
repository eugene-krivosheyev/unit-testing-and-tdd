package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.exception.client.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 0) throw new ClientNegativeIdException("Id can't be negative");
        if (name == null) throw new ClientNameNullException("Name is null");
        if (name.isEmpty()) throw new ClientInvalidNameException("Name is empty");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableCollection(accounts);
    }

    public void setAccount(Account account) {
        if (account == null) throw new ClientAccountsNullException("Accounts are empty");
        if (account.getClient() == this) {
            this.accounts.add(account);
        } else {
            throw new ClientInvalidStateException("Some account has another owner");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(accounts, client.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, accounts);
    }
}
