package com.acme.banking.dbo.service.reporting;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Reporting;
import com.acme.banking.dbo.service.builders.MockitoClientBuilder;
import com.acme.banking.dbo.service.builders.MockitoClientCollectionBuilder;
import com.acme.banking.dbo.service.builders.MockitoSavingAccountBuilder;
import com.acme.banking.dbo.service.builders.MockitoSavingAccountCollectionBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingWithClientsWithStubAccounts {
    private Reporting sut;
    private Branch stubBranch;
    private String report;
    private SavingAccount stubSavingAccount;
    private Collection<SavingAccount> stubSavingAccounts;
    private UUID stubClientId;
    private Client stubClient;
    private Collection<Client> stubClients;

    @Before
    public void setUp() {
        sut = new Reporting();
        stubBranch = mock(Branch.class);
        stubClientId = UUID.randomUUID();
        stubSavingAccount = new MockitoSavingAccountBuilder().withClientWithOtherId(stubClientId).build();
        stubSavingAccounts = new MockitoSavingAccountCollectionBuilder()
                .withSavingAccount(stubSavingAccount)
                .build();
        stubClient = new MockitoClientBuilder().withId(stubClientId).build();
        stubClients = new MockitoClientCollectionBuilder().withClient(stubClient).build();
        when(stubBranch.getClients()).thenReturn(stubClients);
        when(stubClient.getAccounts()).thenReturn(stubSavingAccounts);
    }

    @Test
    public void shouldGetReportWhereClientHasSavingAccounts() {
        //region when
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).endsWith("### savingAccountId");
        //endregion
    }
}
