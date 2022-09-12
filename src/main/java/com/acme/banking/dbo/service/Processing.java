package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Processing {

    private final Cash cash;
    private final ClientService clientService;
    private final AccountService accountService;

    public Client createClient(String name) {
        return clientService.generateNewClient(name);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return accountService.getAccountsByClientId(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account fromAccount = accountService.getAccountById(fromAccountId);
        Account toAccount = accountService.getAccountById(toAccountId);

        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);

        Account fromAccountAfterUpdate = accountService.updateAccount(fromAccount);
        System.out.println(fromAccountAfterUpdate);
        accountService.updateAccount(toAccount);

    }

    public void cash(double amount, int fromAccountId) {
        if (amount <=0) {
            throw new IllegalArgumentException("Amount cann't be less zero");
        }

        cash.log(amount, fromAccountId);
    }
}
