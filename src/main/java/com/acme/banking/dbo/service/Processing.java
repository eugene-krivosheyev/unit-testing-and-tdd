package com.acme.banking.dbo.service;

import java.math.BigDecimal;
import java.util.Collection;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;

public class Processing {
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

//    public Client createClient(String name) {
//        int newId = clientRepository.createUniqueId();
//        return new Client(newId, name);
//    }

//    public Collection<Account> getAccountsByClientId(int clientId) {
//        return accountRepository.getAccountsArray(clientId);
//    }

    public void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {
        if(toAccountId == null) {
            Cash.log(amount, fromAccountId);
        }
    }

//    public void cash(double amount, int fromAccountId) {
//        Cash.log(amount, fromAccountId);
//    }
}
