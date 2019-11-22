package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    private Reporting sut;
    private Branch stubBranch;
    private  String report;

    @Before
    public void setUp() throws Exception {
        sut = new Reporting();
        stubBranch = mock(Branch.class);
    }

    @Test
    public void shouldGetReportWhenBranchHasTwoAccounts() {
        //region given
        //endregion

        //region when
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# ## ClientName1## ClientName2");
        //endregion
    }

    @Test
    public void shouldGetReportWhenBranchHasNoName() {
        when(stubBranch.getName()).thenReturn(null);


        report = sut.getReport(stubBranch);


        assertThat(report).containsOnlyOnce("# ");
    }

    @Test
    public void shouldGetReportWhenBranchIsEmpty() {
        when(stubBranch.getName()).thenReturn("BrunchName");

        report = sut.getReport(stubBranch);

        assertThat(report).containsOnlyOnce("# BrunchName");
    }

    @Test
    public void shouldGetReportWhenBranchClientsCollectionNotEmpty() {
        Collection<Account> stubAccounts = mock(Collection.class);
        when(stubAccounts.isEmpty()).thenReturn(false);
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);

        report = sut.getReport(stubBranch);

        assertThat(report).contains("## clientName");
    }
}
