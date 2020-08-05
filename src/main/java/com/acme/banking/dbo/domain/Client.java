package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;



//import static java.lang.StringLatin1.length;


public class Client {
    private Integer id;
    private String name;
    private Collection<Integer> accountIds = new ArrayList<>(); //TODO

    public Client(Integer id, String name) {
        if ( id == null || id<0) throw new IllegalArgumentException("идентификатор не может быть пустым или меньше 0");
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
