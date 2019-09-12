package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableList;

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

    public List<Account> getAccounts() {
        return unmodifiableList(new ArrayList<>(accounts));
    }

    public List<Branch> getChildren() {
        return unmodifiableList(new ArrayList<>(branches));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
