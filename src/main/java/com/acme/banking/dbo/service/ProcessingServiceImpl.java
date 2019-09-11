package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.dto.AccountDto;
import com.acme.banking.dbo.dto.ClientDto;
import com.acme.banking.dbo.errors.NotFoundException;
import com.acme.banking.dbo.repository.AccountsRepository;
import com.acme.banking.dbo.repository.ClientsRepository;

import java.util.*;

public class ProcessingServiceImpl implements ProcessingService {

    private AccountsRepository accountsRepository;
    private ClientsRepository clientsRepository;
    private Cash cash;

    public ProcessingServiceImpl(
            AccountsRepository accountsRepository,
            ClientsRepository clientsRepository,
            Cash cash) {

        this.accountsRepository = accountsRepository;
        this.clientsRepository = clientsRepository;
        this.cash = cash;
    }

    @Override
    public ClientDto createClient(String name) {
        Client client = clientsRepository.create(name);
        return new ClientDto(client.getId(), client.getName());
    }

    @Override
    public List<AccountDto> getAccountsByClientId(UUID clientId) throws NotFoundException {
        List<Account> accounts = accountsRepository.findAccountsByClientId(clientId);
        if (accounts.isEmpty()) {
            throw new NotFoundException("Accounts not found");
        }

        ArrayList<AccountDto> list = new ArrayList<>();

        for (Account account : accounts) {
            list.add(new AccountDto(account.getId()));
        }

        return Collections.unmodifiableList(list);
    }

    @Override
    public UUID transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        Account fromAccount = accountsRepository.findById(fromAccountId);
        Account toAccount = accountsRepository.findById(toAccountId);
        return accountsRepository.transfer(amount, fromAccount, toAccount);
    }

    @Override
    public void cash(double amount, UUID fromAccountId) {
        cash.log(amount, fromAccountId);
    }
}
