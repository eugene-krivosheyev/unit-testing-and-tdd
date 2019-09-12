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

        public TestBranchRepository build() {

            Client client = new Client(UUID.randomUUID(), "Игорь");
            SavingAccount account = new SavingAccount(UUID.randomUUID(), 3000.0);
            account.setClient(client);
            Branch branchFourth = new Branch(1, "Филиал1", Collections.emptyList(), Collections.singletonList(account));

            Branch branchSecond3 = new Branch(3, "Филиал3", Collections.emptyList(), Collections.emptyList());
            Branch branchSecond2 = new Branch(2, "Филиал2", Collections.emptyList(), Collections.emptyList());
            Branch branchSecond = new Branch(1, "Филиал1", Arrays.asList(branchSecond2, branchSecond3), Collections.emptyList());

            Branch branchThird7 = new Branch(7, "Филиал7", Collections.emptyList(), Collections.emptyList());
            Branch branchThird6 = new Branch(6, "Филиал6", Collections.emptyList(), Collections.emptyList());
            Branch branchThird5 = new Branch(5, "Филиал5", Collections.emptyList(), Collections.emptyList());
            Branch branchThird4 = new Branch(4, "Филиал4", Collections.emptyList(), Collections.emptyList());
            Branch branchThird3 = new Branch(3, "Филиал3", Arrays.asList(branchThird6, branchThird7), Collections.emptyList());
            Branch branchThird2 = new Branch(2, "Филиал2", Arrays.asList(branchThird4, branchThird5), Collections.emptyList());
            Branch branchThird = new Branch(1, "Филиал1", Arrays.asList(branchThird2, branchThird3), Collections.emptyList());

            Branch branchOne = new Branch(1, "Филиал1", Collections.emptyList(), Collections.emptyList());

            when(repository.findBranchById(4)).thenReturn(branchFourth);
            when(repository.findBranchById(3)).thenReturn(branchThird);
            when(repository.findBranchById(2)).thenReturn(branchSecond);
            when(repository.findBranchById(1)).thenReturn(branchOne);

            return new TestBranchRepository(this);
        }
    }
}
