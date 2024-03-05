package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can not be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name can not be blank or empty");
        }
        this.name = name;
    }

    public Client(int id, String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can not be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name can not be blank or empty");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id can not less then 0");
        }
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    // если кл1 утв что владеет счетом 1 то автоматически должно обеспечиваться что счет1 своим хозяином считает кл1
    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts(){
        return Collections.unmodifiableCollection(accounts);
    }

    public void addAccount(Account account) {
        if (account.getClient().getId() != id) {
            throw new IllegalStateException(
                    String.format("account can not be linked to this client, already linked to %s", account.getClient().getId())
            );
        }
        this.accounts.add(account);
    }

}
