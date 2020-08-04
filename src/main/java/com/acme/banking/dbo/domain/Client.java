package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;


//import static java.lang.StringLatin1.length;


public class Client {
    private Integer id;
    private String name;
    private Collection<UUID> accountIds = new ArrayList<>(); //TODO

    public Client(Integer id, String name) {
        if ( id == null || id<0) throw new IllegalArgumentException("неверное значение id");
        if (name == null || name.length()>1000 || name.length()<1) throw new IllegalArgumentException("длина строки должна быть более 0 и менее 1000 символов");

        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
