package com.acme.banking.dbo.domain;

import static com.acme.banking.dbo.domain.Errors.CLIENT_ACCOUNT_ADD_NULL;
import static com.acme.banking.dbo.domain.Errors.CLIENT_ACCOUNT_DUPLICATE;
import static com.acme.banking.dbo.domain.Errors.CLIENT_ACCOUNT_WRONG_OWNER;
import static com.acme.banking.dbo.domain.Errors.CLIENT_EMPTY_NAME_MESSAGE;
import static com.acme.banking.dbo.domain.Errors.CLIENT_NEGATIVE_ID_MESSAGE;
import java.util.*;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        if (id < 0) {
            throw new IllegalArgumentException(CLIENT_NEGATIVE_ID_MESSAGE);
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(CLIENT_EMPTY_NAME_MESSAGE);
        }
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

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalStateException(CLIENT_ACCOUNT_ADD_NULL);
        }
        if (account.getClient().getId() != id) {
            throw new IllegalStateException(CLIENT_ACCOUNT_WRONG_OWNER);
        }
        if (accounts.contains(account)) {
            throw new IllegalStateException(CLIENT_ACCOUNT_DUPLICATE);
        }
        accounts.add(account);
    }

    public void addAccounts(List<SavingAccount> accounts) {
        accounts.forEach(this::addAccount);
    }
}
