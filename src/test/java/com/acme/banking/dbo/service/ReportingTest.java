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
        int branchId = 2;
        final String report = reportingSut.getReport(branchId);
        assertEquals(
        "Moscow Branch" + System.lineSeparator() +
                "============" + System.lineSeparator() +
                "Vasya Puplin" + System.lineSeparator() +
                "------------" + System.lineSeparator() +
                "- account #1: 10.0" + System.lineSeparator() +
                "- account #2: empty" + System.lineSeparator() +
                "Ivan Ivanov"+ System.lineSeparator() +
                "-----------" + System.lineSeparator() +
                "- account #3: 120.0" + System.lineSeparator(),
            report);
    }
}
