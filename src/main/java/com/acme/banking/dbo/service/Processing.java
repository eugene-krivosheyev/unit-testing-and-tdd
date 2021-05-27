package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.ClientRepository;

import java.util.Collection;

public class Processing {
    private ClientRepository clients;

    public Processing(ClientRepository clients) {
        this.clients = clients;
    }

    public Client createClient(Client toSave) {
        if (toSave == null || "".equals(toSave.getName())) throw new IllegalArgumentException();

        return clients.save(toSave);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return null; //TODO
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account from = clients.findById(fromAccountId);
        Account to = clients.findById(toAccountId);

//        from.setAmount(1);
//        to.setAmount(2);

        final SavingAccount updatedFrom = new SavingAccount(
                fromAccountId,
                from.getClient(),
                from.getAmount() - amount
        );

        final SavingAccount updatedTo = new SavingAccount(
                toAccountId,
                to.getClient(),
                to.getAmount() + amount
        );

        clients.update(updatedFrom);
        clients.update(updatedTo);
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
