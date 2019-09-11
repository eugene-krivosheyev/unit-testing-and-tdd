package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepo;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Processing {
    private AccountRepo repo;
    private Cash cash;

    public Processing(AccountRepo repo, Cash cash){
        this.cash = cash;
        this.repo = repo;
    }

    public UUID createClient(String name) {
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("incorrect name");
        return repo.createClient(name);
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        if(clientId==null) throw new IllegalStateException("client is null");
        return repo.findAccountsByUUID(clientId);
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        SavingAccount sourceAcc = repo.findAccountById(fromAccountId);
        SavingAccount descAcc = repo.findAccountById(toAccountId);
        if(sourceAcc.getAmount()>=amount){
            sourceAcc.setAmount(sourceAcc.getAmount()-amount);
            descAcc.setAmount(sourceAcc.getAmount()+amount);
        } else {
            throw new IllegalStateException("invalid amount");
        }
        repo.saveAccounts(Arrays.asList(sourceAcc,descAcc));
    }

    public void cash(double amount, UUID fromAccountId) {
        SavingAccount sourceAcc = repo.findAccountById(fromAccountId);
        sourceAcc.setAmount(sourceAcc.getAmount()-amount);
        cash.log(amount, fromAccountId);
        repo.saveAccounts(Collections.singletonList(sourceAcc));
    }
}
