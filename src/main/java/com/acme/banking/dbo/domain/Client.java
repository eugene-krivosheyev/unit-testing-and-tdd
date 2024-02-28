package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.exception.NameAlphabeticalValidationException;
import com.acme.banking.dbo.exception.IdValidationException;
import com.acme.banking.dbo.exception.NameValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz -";
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        checkClientArguments(id, name);

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void checkClientArguments(int id, String name) {
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
        return StringUtils.isNoneBlank(name) &&
            !StringUtils.startsWith(name, " ") &&
            !StringUtils.endsWith(name, " ") &&
            !StringUtils.startsWith(name, "-") &&
            !StringUtils.endsWith(name, "-")
            ;
    }
}
