package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (name==null){
            throw new IllegalArgumentException("Name can not be null");
        }
        if (name.isBlank()){
            throw new IllegalArgumentException("Name can not be blank or empty");
        }
        if (id < 0){
            throw new IllegalArgumentException("Id can not less then 0");
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
}
