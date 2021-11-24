package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repositories.BranchRepository;
import com.acme.banking.dbo.util.MockClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportingTest {

    private Reporting reportingSut;
    private BranchRepository branchRepositoryDouble;
    private Account accountStub;

    @BeforeEach
    public void setUp() {
        branchRepositoryDouble = mock(BranchRepository.class);
        reportingSut = new Reporting(branchRepositoryDouble);
        accountStub = mock(Account.class);
    }

    @Test
    void shouldGetBranchReportWhenClientsWithAccountExist() {
        int branchId = 2;
        final String report = reportingSut.getReport(branchId);
        assertEquals(
                "Moscow Branch" + System.lineSeparator() +
                        "============" + System.lineSeparator() +
                        "Vasya Puplin" + System.lineSeparator() +
                        "------------" + System.lineSeparator() +
                        "- account #1: 10.0" + System.lineSeparator() +
                        "- account #2: empty" + System.lineSeparator() +
                        "Ivan Ivanov" + System.lineSeparator() +
                        "-----------" + System.lineSeparator() +
                        "- account #3: 120.0" + System.lineSeparator(),
                report);
    }

    @Test
    void shouldGetBranchReportWithEmptyMoneyAmount() {
        int branchId = 2;
        final String report = reportingSut.getReport(branchId);
        assertEquals(
                "Moscow Branch" + System.lineSeparator() +
                        "============" + System.lineSeparator() +
                        "Vasya Puplin" + System.lineSeparator() +
                        "------------" + System.lineSeparator() +
                        "- account #1: empty" + System.lineSeparator(),
                report);
    }

    @Test
    void shouldGetReportForClient() {
        Client clientStub = new MockClientBuilder().build();
        String report = reportingSut.getReport(clientStub);

        assertEquals(
                "Vasya Puplin" + System.lineSeparator() +
                        "------------" + System.lineSeparator() +
                        "- account #1: 10.0" + System.lineSeparator(),
                report);
    }

    @Test
    void shouldGetReportForClientWithEmptyMoneyAmount() {
        Client clientStub = new MockClientBuilder().build();
        String report = reportingSut.getReport(clientStub);

        assertEquals(
                "Vasya Puplin" + System.lineSeparator() +
                        "------------" + System.lineSeparator() +
                        "- account #1: empty" + System.lineSeparator(),
                report);
    }

    @Test
    void shouldGetReportForAccountWithEmptyMoneyAmount() {
        // Given
        when(accountStub.getAmount()).thenReturn(0.0);
        when(accountStub.getId()).thenReturn(1);

        // When
        String report = reportingSut.getReport(accountStub);

        // Then
        assertEquals("- account #1: empty" + System.lineSeparator(),
                report);
    }

    @Test
    void shouldGetReportForAccount() {
        // Given
        when(accountStub.getAmount()).thenReturn(10.0);
        when(accountStub.getId()).thenReturn(1);

        // When
        String report = reportingSut.getReport(accountStub);

        // Then
        assertEquals("- account #1: 10.0" + System.lineSeparator(),
                report);
    }
}