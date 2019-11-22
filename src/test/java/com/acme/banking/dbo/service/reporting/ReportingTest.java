package com.acme.banking.dbo.service.reporting;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.Reporting;
import com.acme.banking.dbo.service.builders.MockitoAccountCollectionBuilder;
import com.acme.banking.dbo.service.builders.MockitoClientBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    private Reporting sut;
    private Branch stubBranch;
    private String report;

    @Before
    public void setUp() {
        sut = new Reporting();
        stubBranch = mock(Branch.class);
    }

    @Test
    public void shouldGetReportWithNewLinesWhenBranchHasAccount() throws ClassNotFoundException {
        //region given
        Collection<Account> stubAccounts = new MockitoAccountCollectionBuilder().withCount(1).build();
        //endregion

        //region when
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# \n## clientName");
        //endregion
    }

    @Test
    @Ignore
    public void shouldGetReportWithAccountIdWhenBranchHasAccount() throws ClassNotFoundException {
        //region given
        UUID stubClientId = UUID.randomUUID();
        Account stubAccount = new MockitoClientBuilder().withId(stubClientId).build();
        Collection<Account> stubAccounts = new MockitoAccountCollectionBuilder()
                .withAccount(stubAccount)
                .build();
        //endregion

        //region when
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        when(stubAccount.getClientId()).thenReturn(stubClientId);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# ## " + stubClientId);
        //endregion
    }


    @Test
    @Ignore
    public void shouldGetReportWhenBranchHasTwoAccounts() throws ClassNotFoundException {
        //region given
        Collection<Account> stubAccounts = new MockitoAccountCollectionBuilder().withCount(2).build();
        //endregion

        //region when
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# ## ClientName1## ClientName2");
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
        //region given
        Collection<Account> stubAccounts = mock(Collection.class);
        //endregionl

        //region when
        when(stubAccounts.isEmpty()).thenReturn(false);
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).contains("## clientName");
        //endregion
    }
}
