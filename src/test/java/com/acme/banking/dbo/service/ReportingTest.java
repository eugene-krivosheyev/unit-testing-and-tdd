package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.Reporting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    private Reporting reportingSut;
    private Account accountStub;

    @BeforeEach
    public void setUp() {
        reportingSut = new Reporting();
        accountStub = mock(Account.class);
    }

    @Disabled @Test
    public void shouldGetBranchReportWhenClientsWithAccountExist() {
        final Branch branchStub = mock(Branch.class);

        final String report = reportingSut.getReport(branchStub);
        assertEquals(
        "Moscow Branch" + System.lineSeparator() +
                "============" + System.lineSeparator() +
                "Vasya Puplin" + System.lineSeparator() +
                "------------" + System.lineSeparator() +
                "- account #1: 10.0" + System.lineSeparator() +
                "- account #2: 0.0" + System.lineSeparator() +
                "Ivan Ivanov"+ System.lineSeparator() +
                "-----------" + System.lineSeparator() +
                "- account #3: 120.0" + System.lineSeparator(),
            report);
    }

    @Test
    public void shouldGetAccountReportWhenEmptyAccount() {
        when(accountStub.getId()).thenReturn(3);
        when(accountStub.getAmount()).thenReturn(0.);

        String report = reportingSut.getReport(accountStub);

        assertEquals(
                "- account #3: 0.0",
                report
        );
    }

    @Test
    public void shouldGetAccountReportWhenPositiveAccount() {
        when(accountStub.getId()).thenReturn(4);
        when(accountStub.getAmount()).thenReturn(10.);

        String report = reportingSut.getReport(accountStub);

        assertEquals(
                "- account #4: 10.0",
                report
        );
    }

    @Test
    @Disabled
    public void shouldGetClientReportWhenAccountsExist() {
//        when(accountStub.getId()).thenThrow(???);


//        String report = reportingSut.getReport(???);
//        assertEquals("????", report);
    }
}
