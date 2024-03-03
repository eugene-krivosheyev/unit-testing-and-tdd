package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.exceptions.CreateClientException;
import com.acme.banking.dbo.exceptions.GetAccountsException;
import com.acme.banking.dbo.exceptions.TransferException;
import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.repo.ClientRepository;

import java.util.Collection;

public class Processing {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public Processing (ClientRepository clientRepository, AccountRepository accountRepository){
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client createClient(String name) {
        int id = clientRepository.generateId();
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
        try {
            Client client = clientRepository.getClientById(clientId);
            return client.getAccounts();
        } catch (Exception e){
            throw new GetAccountsException("Cannot get accounts for client!");
        }
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {

        if (fromAccountId == toAccountId) throw new TransferException("The source account is the same as the target!");
        if (amount <= 0) throw new TransferException("Amount " + amount + " is invalid value!");
        Account fromAccount;
        Account toAccount;

        try {
            fromAccount = accountRepository.getAccountById(fromAccountId);
            toAccount = accountRepository.getAccountById(toAccountId);
        } catch (Exception e){
            throw new TransferException("Account service returned error");
        }

        double fromAccountBalance = fromAccount.getAmount();
        double toAccountBalance = toAccount.getAmount();

        if (fromAccountBalance < amount) throw new TransferException("Not enough money to transfer");

        try {
            fromAccount.setBalance(fromAccountBalance - amount);
            toAccount.setBalance(toAccountBalance + amount);
        } catch(Exception e){
            throw new TransferException("Fail transaction");
        }
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }


}
