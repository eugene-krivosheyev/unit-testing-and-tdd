package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.exception.OverdraftException;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.impl.AccountRepositoryImpl;

public class SavingAccount implements Account {

    private static AccountRepository repo = new AccountRepositoryImpl();
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        this.id = id;
        this.client = client;
        this.amount = amount;

        this.client.addAccount(this);
        repo.registerAccount(this);
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }

    public void setClient(Client newClient) {
        if (client.getId() != newClient.getId()){
            client.removeAccount(this);
        }

        this.client = newClient;
        this.client.addAccount(this);
    }

    @Override
    public void changeBalanceTo(double amount) {
        if (this.amount + amount < 0){
            throw new OverdraftException();
        }
        this.amount = this.amount + amount;
    }

    public void unlinkClient(){
        this.client.removeAccount(this);
        this.client = null;
    }
}
