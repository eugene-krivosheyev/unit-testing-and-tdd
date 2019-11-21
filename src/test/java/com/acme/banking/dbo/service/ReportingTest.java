package com.acme.banking.dbo.service;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    @Test
    public void shouldGetReportWhenBranchHasClients(){
        Reporting sut = new Reporting();
        Branch stubBranch = mock(Branch.class);
        Collection<Account> stubAccounts = mock(Collection.class);
        when(stubAccounts.isEmpty()).thenReturn(false);
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        String report  = sut.getReport(stubBranch);

        assertThat(report).contains("## clientName");
    }
    @Test
    public void shouldGetReportWhenBranchIsEmpty(){
        Reporting sut = new Reporting();
        Branch stubBranch = mock(Branch.class);
        when(stubBranch.getName()).thenReturn("BrunchName");
        String report = sut.getReport(stubBranch);
        assertThat(report).containsOnlyOnce("# BrunchName");
    }
}
