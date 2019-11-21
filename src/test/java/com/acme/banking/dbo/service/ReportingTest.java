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
    public void shouldGetReportWhenBranchNotEmpty(){
        Reporting sut = new Reporting();
        Branch stubBranch= mock(Branch.class);
        Collection<Account> stubAccounts =new ArrayList<>();
        final Account stubAccount = mock(Account.class);
        stubAccounts.add(stubAccount);
        when(stubBranch.getAccounts()).thenReturn(stubAccounts);
        String report  = sut.getReport(stubBranch);

        assertThat(report).contains("## "+stubAccount.getId());

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
