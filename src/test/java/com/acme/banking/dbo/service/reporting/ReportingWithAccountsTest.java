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

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingWithAccountsTest extends ReportingTest {
    private Reporting sut;
    private Branch stubBranch;
    private SavingAccount stubSavingAccount;
    private String report;
    private UUID stubClientId;
    private Collection<SavingAccount> stubAccounts;
    private Client stubClient;
    private Collection<Client> stubClients;

    @Before
    public void setUp() throws ClassNotFoundException {
        sut = new Reporting();
        stubBranch = mock(Branch.class);
        stubClientId = UUID.randomUUID();
        stubSavingAccount = new MockitoSavingAccountBuilder().withClientWithOtherId(stubClientId).build();
        stubAccounts = new MockitoSavingAccountCollectionBuilder()
                .withSavingAccount(stubSavingAccount)
                .build();
        stubClient = new MockitoClientBuilder().withId(stubClientId).build();
        stubClients = new MockitoClientCollectionBuilder().withClient(stubClient).build();
    }

    @Test
    public void shouldGetReportWhereClientHasSavingAccounts() {
        //region given
        Client stubClient1 = new MockitoClientBuilder().build();
        Collection<Client> stubClients1 = new MockitoClientCollectionBuilder().withClient(stubClient1).build();
        SavingAccount stubSavingAccount = new MockitoSavingAccountBuilder().build();
        Collection<SavingAccount> stubSavingAccounts = new ArrayList<>();
        stubSavingAccounts.add(stubSavingAccount);
        //endregion

        //region when
        when(this.stubSavingAccount.getClient()).thenReturn(stubClient1);
        when(stubBranch.getClients()).thenReturn(stubClients1);
        when(stubClient1.getAccounts()).thenReturn(stubSavingAccounts);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).endsWith("### savingAccountId");
        //endregion
    }

    @Test
    public void shouldGetReportWithNewLinesWhenBranchHasAccount() {
        //region when
        when(stubBranch.getClients()).thenReturn(stubClients);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# \n## " + stubClientId);
        //endregion
    }

    @Test
    public void shouldGetReportWithAccountIdWhenBranchHasAccount() {
        //region when
        when(stubBranch.getClients()).thenReturn(stubClients);
        when(stubSavingAccount.getClientId()).thenReturn(stubClientId);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# \n## " + stubClientId);
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchHasTwoAccounts() throws ClassNotFoundException {
        //region given
        UUID stubClientId2 = UUID.randomUUID();
        stubClients = new MockitoClientCollectionBuilder()
                .withClient(new MockitoClientBuilder().withId(stubClientId).build())
                .withClient(new MockitoClientBuilder().withId(stubClientId2).build())
                .build();
        //endregion

        //region when
        when(stubBranch.getClients()).thenReturn(stubClients);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# \n## " + stubClientId + "## " + stubClientId2);
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchHasNoName() {
        //region when
        when(stubBranch.getName()).thenReturn(null);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# ");
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchIsEmpty() {
        //region when
        when(stubBranch.getName()).thenReturn("BrunchName");
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# BrunchName");
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchClientsCollectionNotEmpty() {
        //region when
        when(stubBranch.getClients()).thenReturn(stubClients);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# \n## " + stubClientId);
        //endregion
    }
}
