package com.acme.banking.dbo.domain;

import com.sun.xml.internal.ws.client.ClientSchemaValidationTube;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private String name;
    private Collection<Branch> children;
    private Collection<Client> clients; //TODO impl

    public Branch(String name) {
        this.name = name;
    }

    public Branch(Collection<Client> clients) {
        this.clients = clients;
    }

    public String getName() {
        return name;
    }

    public Collection<Client> getClients() {
        return unmodifiableCollection(clients);
    }

    public Collection<Branch> getChildren() {
        return unmodifiableCollection(children);
    }
}
