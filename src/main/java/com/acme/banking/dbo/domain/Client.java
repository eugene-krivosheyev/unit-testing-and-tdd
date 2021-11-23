package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name is empty.");
        if (id < 0) throw new IllegalArgumentException("Id is negative");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(SavingAccount account) {
        if (account == null) throw  new IllegalArgumentException("account is null");

        this.accounts.add(account);
    }


    public Collection<Account> getAccounts() {
        return accounts;
    }

    public static class ClientBuilder {
        static int id = 1;
        static String name = "a";

        private void ClientBuilder() {
        }

        public ClientBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ClientBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public Client build() {
            return new Client(id, name);
        }
    }
}

