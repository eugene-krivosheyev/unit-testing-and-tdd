package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.UUID;

public class SavingAcountBuilder {
    private UUID id;
    private Client client;
    private double amount;

    public void UUID(UUID id){
        this.id=id;
    }

    public void client(Client client){
        this.client=client;
    }
    public void amount(double amount){
        this.amount=amount;
    }

    public SavingAccount build(){
        return new SavingAccount(id,client,amount);
    }


}
