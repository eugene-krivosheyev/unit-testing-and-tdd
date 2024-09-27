package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.acme.banking.dbo.exception.IdValidationException;
import com.acme.banking.dbo.exception.NameAlphabeticalValidationException;
import com.acme.banking.dbo.exception.NameValidationException;
import org.apache.commons.lang3.StringUtils;

public class Client {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz -";
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        validateClientArguments(id, name);

        this.id = id;
        this.name = name;
    }

    public Client addAccount(Account account) {
        if (accounts.contains(account)) {
            return this;
        }
        accounts.add(account);
        account.setClient(this);
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void validateClientArguments(int id, String name) {
        if (id < 0) {
            throw new IdValidationException();

        }
        if (!isNameValid(name)) {
            throw new NameValidationException();
        }

        for (Character ch : name.toCharArray()) {
            if (!ALPHABET.contains(ch.toString().toLowerCase())) {
                throw new NameAlphabeticalValidationException();
            }
        }
    }

    private boolean isNameValid(String name) {
        return StringUtils.isNoneBlank(name) && !StringUtils.startsWith(name, " ") && !StringUtils.endsWith(name,
                " ") && !StringUtils.startsWith(name, "-") && !StringUtils.endsWith(name, "-");
    }

    public void removeAccount(SavingAccount savingAccount) {
        if (!this.accounts.contains(savingAccount)) {
            return;
        }
        this.accounts.remove(savingAccount);
        savingAccount.unlinkClient();
    }

    public Collection<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) &&
                Objects.equals(accounts, client.accounts);
    }

    @Override public int hashCode() {
        return Objects.hash(id, name, accounts);
    }
}
