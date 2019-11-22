package com.acme.banking.dbo.service.reporting;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.Reporting;
import com.acme.banking.dbo.service.builders.MockitoAccountCollectionBuilder;
import com.acme.banking.dbo.service.builders.MockitoClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    private Reporting sut;
    private Branch stubBranch;
    private Account stubAccount;
    private String report;
    private UUID stubClientId;
    private Collection<Account> stubAccounts;

    @Before
    public void setUp() throws ClassNotFoundException {
        sut = new Reporting();
        stubBranch = mock(Branch.class);
        stubClientId = UUID.randomUUID();
        stubAccount = new MockitoClientBuilder().withId(stubClientId).build();
        stubAccounts = new MockitoAccountCollectionBuilder()
                .withAccount(stubAccount)
                .build();
    }


}
