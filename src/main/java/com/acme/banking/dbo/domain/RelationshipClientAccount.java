package com.acme.banking.dbo.domain;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RelationshipClientAccount {
    private Client client;
    private List<SavingAccount> savingAccountList;

    public RelationshipClientAccount(Client client, List<SavingAccount> savingAccountList) {
        if(client == null) throw new IllegalArgumentException("client = null");
        if (savingAccountList == null || savingAccountList.isEmpty()) throw new IllegalArgumentException("savingAccountList = null or empty");

        this.client = client;
        this.savingAccountList = savingAccountList;
    }

    public Client getClient() {
        return client;
    }

    public List<SavingAccount> getSavingAccountList() {
        return Collections.unmodifiableList(savingAccountList);
    }
}
