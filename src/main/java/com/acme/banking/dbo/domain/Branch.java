package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private Collection<Account> accounts; //TODO

    private String name;

    private Collection<Branch> childs = new ArrayList<>();

    public Branch(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        return new ArrayList<>(childs);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
