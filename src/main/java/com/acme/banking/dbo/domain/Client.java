package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.exception.client.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 0) throw new IllegalClientIdArgumentException("Id can't be negative");
        if (name == null) throw new ClientNameNullException("Name is null");
        if (name.isEmpty()) throw new IllegalClientNameArgumentException("Name is empty");

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
            throw new IllegalClientStateException("Some account has another owner");
        }
    }
}
