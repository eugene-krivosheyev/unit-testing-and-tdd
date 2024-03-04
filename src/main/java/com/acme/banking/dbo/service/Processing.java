package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.CashLogService;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public class Processing {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private CashLogService cashLogService;

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository, CashLogService cashLogService) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.cashLogService = cashLogService;
    }

    public Client createClient(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("ClientName is invalid");
        }
        return clientRepository.saveClientWithName(name);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        if (clientId < 0) {
            throw new IllegalArgumentException("ClientId is invalid");
        }
        return accountRepository.findAccountsByClient(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        var accountFrom = accountRepository.findAccountById(fromAccountId);
        if (accountFrom == null) {
            throw new IllegalArgumentException();
        }

        var accountTo = accountRepository.findAccountById(toAccountId);
        if (accountTo == null) {
            throw new IllegalArgumentException();
        }

        if (accountFrom.getAmount() < amount) {
            throw new IllegalArgumentException();
        }

        accountTo.setAmount(accountTo.getAmount() + amount);
        accountFrom.setAmount(accountFrom.getAmount() - amount);

        var updatedFromAccount = accountRepository.updateClientAccount(accountFrom);
        if (updatedFromAccount.getAmount() != accountFrom.getAmount()) {
            throw new IllegalStateException();
        }

        var updatedToAccount = accountRepository.updateClientAccount(accountTo);
        if (updatedToAccount.getAmount() != accountTo.getAmount()) {
            throw new IllegalStateException();
        }
    }

    public void logCash(double amount, int fromAccountId) {
        if (fromAccountId < 0) {
            throw new IllegalArgumentException("Invalid accountId");
        }
        cashLogService.logCash(amount, fromAccountId);
    }
}
