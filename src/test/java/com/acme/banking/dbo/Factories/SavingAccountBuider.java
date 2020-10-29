package com.acme.banking.dbo.Factories;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.UUID;

public class SavingAccountBuider {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    private UUID _id;
    private Client _client;
    private double _amount;


    public SavingAccountBuider SetId(UUID id)
    {
        _id = id;
        return this;
    }

    public SavingAccountBuider SetAmount(double amount)
    {
        _amount = amount;
        return this;
    }

    public SavingAccountBuider SetClient(Client client)
    {
        _client = client;
        return this;
    }

    public SavingAccount Build ()
    {
        return new Client(_id, _name);
    }

}
