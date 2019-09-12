package com.acme.banking.dbo.domain;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private final long id;
    private final String name;
    private Collection<Account> accounts; //TODO impl
    private int level;

    public Branch(long id, String name, int level, Collection<Account> accounts) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.accounts = accounts;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        //TODO
        return null;
    }

    public int getLevel() {
        return level;
    }
}
