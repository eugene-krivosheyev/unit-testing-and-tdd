package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public static class Err {
        public static final String BAD_ID = "Bad id";
        public static final String BAD_NAME = "Bad name";
        public static final String CLIENT_DOESNT_MATCH = "Client doesn't match";
    }

    public static class Warn {
        public static final String CLIENT_ALREADY_ESITS = "Account is already exist";
    }

    public Client(int id, String name) {
        if (id < 0) throw new IllegalArgumentException(Err.BAD_ID);
        if (name == null || name.isEmpty()) throw new IllegalArgumentException(Err.BAD_NAME);

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

    public void addAccount(Account acc) {
        if ( ! acc.getClient().equals(this) ) throw new IllegalArgumentException(Err.CLIENT_DOESNT_MATCH);
        if ( accounts.contains(acc) ) {
            System.out.println(Warn.CLIENT_ALREADY_ESITS);
            return;
        }
        accounts.add(acc);
    }
}
