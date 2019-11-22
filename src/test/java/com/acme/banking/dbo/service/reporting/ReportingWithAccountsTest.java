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

public class ReportingWithAccountsTest extends ReportingTest {
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

    @Test
    public void shouldGetReportWithNewLinesWhenBranchHasAccount() {
        //region when
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# \n## " + stubClientId);
        //endregion
    }

    @Test
    public void shouldGetReportWithAccountIdWhenBranchHasAccount() {
        //region when
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        when(stubAccount.getClientId()).thenReturn(stubClientId);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# \n## " + stubClientId);
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchHasTwoAccounts() throws ClassNotFoundException {
        //region given
        UUID stubClientId2 = UUID.randomUUID();
        stubAccounts = new MockitoAccountCollectionBuilder()
                .withAccount(new MockitoClientBuilder().withId(stubClientId).build())
                .withAccount(new MockitoClientBuilder().withId(stubClientId2).build())
                .build();
        //endregion

        //region when
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# \n## " + stubClientId + "## " + stubClientId2);
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchHasNoName() {
        //region when
        when(stubBranch.getName()).thenReturn(null);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).containsOnlyOnce("# ");
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchIsEmpty() {
        //region when
        when(stubBranch.getName()).thenReturn("BrunchName");
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).containsOnlyOnce("# BrunchName");
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchClientsCollectionNotEmpty() {
        //region when
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# \n## " + stubClientId);
        //endregion
    }
}
