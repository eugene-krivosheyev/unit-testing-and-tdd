package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.CashLogService;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public class Processing {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private CashLogService cashLogService;

    public Processing(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Processing(CashLogService cashLogService) {
        this.cashLogService = cashLogService;
    }

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository, CashLogService cashLogService) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.cashLogService = cashLogService;
    }

    public Client createClient(String name) {
        return clientRepository.saveClientWithName(name);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return accountRepository.findAccountsByClient(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        var accountFrom = accountRepository.findAccountById(fromAccountId);
        var accountTo = accountRepository.findAccountById(toAccountId);
//        accountFrom.s

    }

    public void cash(double amount, int fromAccountId) {
        cashLogService.log(amount, fromAccountId);
    }
}
