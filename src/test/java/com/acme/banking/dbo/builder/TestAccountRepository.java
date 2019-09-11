package com.acme.banking.dbo.builder;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.AccountsRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAccountRepository {

    private final AccountsRepository repository;
    private final Account fromAccount;
    private final Account toAccount;
    private final double transferAmount;
    private final UUID transferId;
    private final UUID id;

    private TestAccountRepository(Builder builder) {
        repository = builder.repository;
        fromAccount = builder.fromAccount;
        toAccount = builder.toAccount;
        transferAmount = builder.transferAmount;
        transferId = builder.transferId;
        id = builder.id;
    }

    public AccountsRepository getRepository() {
        return repository;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public UUID getTransferId() {
        return transferId;
    }

    public UUID getId() {
        return id;
    }

    public static class Builder {
        private AccountsRepository repository = mock(AccountsRepository.class);
        private double amount;
        private double transferAmount;
        private UUID id;
        private UUID fromId;
        private UUID toId;
        private UUID transferId;
        private Account fromAccount;
        private Account toAccount;

        public Builder() {
            amount = 0.3;
            transferAmount = 0.2;
            id = UUID.randomUUID();
            fromId = UUID.randomUUID();
            toId = UUID.randomUUID();
            transferId = UUID.randomUUID();
        }

        public Builder setTransferAmount(double value) {
            transferAmount = value;
            return this;
        }

        public TestAccountRepository build() {
            List<Account> stubAccounts = Collections.singletonList(new SavingAccount(id, amount));
            fromAccount = new SavingAccount(fromId, amount);
            toAccount = new SavingAccount(toId, amount);

            when(repository.findAccountsByClientId(id)).thenReturn(stubAccounts);
            when(repository.findById(fromId)).thenReturn(fromAccount);
            when(repository.findById(toId)).thenReturn(toAccount);
            when(repository.transfer(transferAmount, fromAccount, toAccount)).thenReturn(transferId);

            return new TestAccountRepository(this);
        }
    }
}
