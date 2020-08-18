package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private Integer id;
    private Client client;
    private double amount;

    public SavingAccount(Integer id, Client client, double amount) {
        if ( id == null || id<0) throw new IllegalArgumentException("идентификатор не может быть пустым или меньше 0");
        if (client == null) throw new IllegalArgumentException("клиент не может быть пустым");
        if ( amount<0) throw new IllegalArgumentException("остаток на счете должен быть более 0");

        this.id = id;
        this.client = client;
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getClientId() {
        return client.getId();
    }

    @Override
    public void withdraw(double amount){
        this.amount = this.amount - amount;
    }

    @Override
    public void deposit(double amount){
        this.amount = this.amount + amount;
    }

    @Override
    public void getAccountReport() {

        System.out.print(id.toString() + " " + Double.toString(amount) );

    }

}
