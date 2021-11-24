package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportingTest {

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

    //                        "- account #3: 120.0" + System.lineSeparator(),

    @Test
    public void shouldGetAccountReportWhenNotEmptyAccount() {

        //accountStub When
        Client clientStub = mock(Client.class);
        when(clientStub.getName()).thenReturn("Ivan Ivanov");

        when(accountStub.getId()).thenReturn(3);
        when(accountStub.getAmount()).thenReturn(120.0);

        Collection<Account> clientAccounts = new ArrayList<Account>();
        clientAccounts.add(accountStub);


        when(clientStub.getAccounts()).thenReturn(clientAccounts);

        final String report = reportingSut.getClientReport(clientStub);

        assertEquals(
                "Ivan Ivanov"+ System.lineSeparator() +
                        "-----------" + System.lineSeparator() +
                        "- account #3: 120.0" + System.lineSeparator(),
                report
        );
    }

    @Test
    public void shouldGetAccountReportWhenEmptyAccount() {

        //accountStub When
        Client clientStub = mock(Client.class);
        when(clientStub.getName()).thenReturn("Ivan Ivanov");

        when(accountStub.getId()).thenReturn(3);
        when(accountStub.getAmount()).thenReturn(0.0);

        Collection<Account> clientAccounts = null;
        clientAccounts.add(accountStub);

        when(clientStub.getAccounts()).thenReturn(clientAccounts);

        final String report = reportingSut.getClientReport(clientStub);

        assertEquals(
                "Ivan Ivanov"+ System.lineSeparator() +
                        "-----------" + System.lineSeparator() +
                        "- account #3: empty" + System.lineSeparator(),
                report
        );
    }

//
//    @Test
//    void shouldGetAccountReportWhenEmptyAccount() {
//        when(accountStub.getId()).thenReturn(3);
//        when(accountStub.getAmount()).thenReturn(0.);
//
//        String report = reportingSut.getReport(accountStub);
//
//        assertEquals(
//                "- account #3: empty",
//                report
//        );
//    }
//
//    @Test
//    void shouldGetAccountReportWhenPositiveAccount() {
//        when(accountStub.getId()).thenReturn(4);
//        when(accountStub.getAmount()).thenReturn(120.);
//
//        String report = reportingSut.getReport(accountStub);
//
//        assertEquals(
//                "- account #4: 120.0",
//                report
//        );
//    }
//
//
//    @Test
//    public void shouldGetClientReportWhenAccountExist() {
//        String report = reportingSut.getReport(accountStub);
//
//        // AssertionFirst pattern
//        assertEquals(
//                "??",
//                report);
//    }
}
