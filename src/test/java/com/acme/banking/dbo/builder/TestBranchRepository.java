package com.acme.banking.dbo.builder;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.BranchRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestBranchRepository {

    private final BranchRepository repository;

    private TestBranchRepository(Builder builder) {
        repository = builder.repository;
    }

    public BranchRepository getRepository() {
        return repository;
    }

    public static class Builder {
        private BranchRepository repository = mock(BranchRepository.class);

        public Builder() {}

        public Builder setFilialWithEmptyBranches() {
            Branch branch = new Branch(1, "Филиал1", Collections.emptyList(), Collections.emptyList());
            when(repository.findBranchById(1)).thenReturn(branch);
            return this;
        }

        public Builder setFilialWithAccounts() {
            Client client = new Client(UUID.randomUUID(), "Игорь");
            SavingAccount account = new SavingAccount(UUID.randomUUID(), 3000.0);
            account.setClient(client);
            Branch branch = new Branch(1, "Филиал1", Collections.emptyList(), Collections.singletonList(account));
            when(repository.findBranchById(4)).thenReturn(branch);
            return this;
        }

        public Builder setFilialWithOneLevelBranch() {
            Branch branch3 = new Branch(3, "Филиал3", Collections.emptyList(), Collections.emptyList());
            Branch branch2 = new Branch(2, "Филиал2", Collections.emptyList(), Collections.emptyList());
            Branch branch = new Branch(1, "Филиал1", Arrays.asList(branch2, branch3), Collections.emptyList());
            when(repository.findBranchById(2)).thenReturn(branch);
            return this;
        }

        public Builder setFilialWithBranches() {
            Branch branch7 = new Branch(7, "Филиал7", Collections.emptyList(), Collections.emptyList());
            Branch branch6 = new Branch(6, "Филиал6", Collections.emptyList(), Collections.emptyList());
            Branch branch5 = new Branch(5, "Филиал5", Collections.emptyList(), Collections.emptyList());
            Branch branch4 = new Branch(4, "Филиал4", Collections.emptyList(), Collections.emptyList());
            Branch branch3 = new Branch(3, "Филиал3", Arrays.asList(branch6, branch7), Collections.emptyList());
            Branch branch2 = new Branch(2, "Филиал2", Arrays.asList(branch4, branch5), Collections.emptyList());
            Branch branch = new Branch(1, "Филиал1", Arrays.asList(branch2, branch3), Collections.emptyList());
            when(repository.findBranchById(3)).thenReturn(branch);
            return this;
        }

        public Builder setFilialWithEmptyClient() {
            SavingAccount account = new SavingAccount(UUID.randomUUID(), 3000.0);
            Branch branch = new Branch(1, "Филиал1", Collections.emptyList(), Collections.singletonList(account));
            when(repository.findBranchById(5)).thenReturn(branch);
            return this;
        }

        public TestBranchRepository build() {
            return new TestBranchRepository(this);
        }
    }
}
