package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        checkArguments(id, name);
        this.id = id;
        this.name = name;
    }

    public Client(String name) {
        checkName(name);
        this.name = name;
    }

    private void checkName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private void checkArguments(int id, String name) {
        checkId(id);
        checkName(name);
    }

    private void checkId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        if (account == null || !account.getClient().equals(this)) {
            throw new IllegalArgumentException();
        }
        accounts.add(account);
    }
}
