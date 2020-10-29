package com.acme.banking.dbo.builder;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class ClientBuilder {
    private UUID id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

    public ClientBuilder UUID(UUID id) {
        this.id=id;
        return this;
    }

    public ClientBuilder name(String name){
        this.name=name;
        return this;
    }

    public ClientBuilder accounts(Collection<Account> accounts){
        this.accounts=accounts;
        return this;
    }

    public Client build(){
        return new Client(id,name,accounts);
    }

}
