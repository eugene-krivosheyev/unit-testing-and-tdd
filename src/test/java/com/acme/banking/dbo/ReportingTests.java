package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class ReportingTests {

    @Test
    public void shouldReturnEmptyBrunchNameWhenBrunchIsNotExist() {
        Reporting sut = new Reporting();

        String result = sut.getReport(null);

        Assert.assertEquals("Empty brunch", result);
    }

    @Test
    public void shouldReturnBranchNameWhenBrunchExists() {
        Reporting sut = new Reporting();
        Branch stubBranch = mock(Branch.class);

        when(stubBranch.getName()).thenReturn("Branch name");

        String result = sut.getReport(stubBranch);

        Assert.assertEquals("Branch name", result);
    }
    @Test
    public void shouldReturnAccountsListWhenBrunchHasAccounts() {
        Reporting sut = new Reporting();
        Branch stubBranch = mock(Branch.class);
        Account stubAccount = mock(Account.class);

        UUID dummyId = UUID.randomUUID();
        
        when(stubAccount.getId()).thenReturn(dummyId);
        when(stubBranch.getName()).thenReturn("Branch name");
        when(stubBranch.getAccounts()).thenReturn(Arrays.asList(stubAccount));

        String result = sut.getReport(stubBranch);

        Assert.assertEquals("Branch name" + dummyId, result);
    }

}
