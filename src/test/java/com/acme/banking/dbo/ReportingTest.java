package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ReportingTest {
    Reporting sut;
    private Account stubAccount;

    @Before
    public void test() {
        sut = new Reporting();
        stubAccount = mock(Account.class);
    }

    @Test
    public void shouldGetReportForAccountWhenAmountIsEmpty() {
        when(stubAccount.getAmount()).thenReturn(0.);
        assertEquals(
                "ACCOUNT 1 EMPTY", sut.getReport(stubAccount)
        );
    }

    @Test
    public void shouldGetReportForAccountWhenAmountIsNotEmpty() {
        when(stubAccount.getAmount()).thenReturn(1.);
        assertEquals(
                "1.0", sut.getReport(stubAccount)
        );
    }

}
