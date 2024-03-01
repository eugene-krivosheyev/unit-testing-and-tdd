package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.exceptions.CreateClientException;
import com.acme.banking.dbo.repo.ClientRepository;

import java.util.Collection;

public class Processing {

    private ClientRepository clientRepository;

    public Processing (ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client createClient(String name) {
        int id=clientRepository.generateId();
        Client client = new Client(id,  name);
        Client savedClient;
        try {
            savedClient = clientRepository.save(client);
        } catch (Exception e){
            throw new CreateClientException("Error during save client!");
        }

        return savedClient;
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return null; //TODO
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        //TODO
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
