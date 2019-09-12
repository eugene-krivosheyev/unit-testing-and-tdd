package com.acme.banking.dbo.domain;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private int id;
    private String name;
    private Collection<Account> accounts;
    private Collection<Branch> branches;

    public Branch(int id, String name, Collection<Branch> branches, Collection<Account> accounts) {
        this.branches = branches;
        this.accounts = accounts;
        this.id = id;
        this.name = name;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        return unmodifiableCollection(branches);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
