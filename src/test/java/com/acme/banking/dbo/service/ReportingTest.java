package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repositories.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportingTest {

    private Reporting reportingSut;
    private BranchRepository branchRepositoryDouble;
    private Client clientStub;
    private Account accountStub;
    private Branch branchStub;

    @BeforeEach
    public void setUp() {
        branchStub = mock(Branch.class);
        clientStub = mock(Client.class);
        branchRepositoryDouble = mock(BranchRepository.class);
        reportingSut = new Reporting(branchRepositoryDouble);
        accountStub = mock(Account.class);
    }

    @Test
    void shouldGetBranchReportWhenClientsWithAccountExist() {
        // Given
        when(branchRepositoryDouble.getBranchById(1)).thenReturn(branchStub);
        when(branchStub.getName()).thenReturn("Moscow Branch");
        when(branchStub.getBranchClients()).thenReturn(singletonList(clientStub));
        when(clientStub.getName()).thenReturn("Vasya Puplin");
        when(clientStub.getAccounts()).thenReturn(singletonList(accountStub));
        when(accountStub.getAmount()).thenReturn(10.0);
        when(accountStub.getId()).thenReturn(1);

        // When
        final String report = reportingSut.getReport(1);

        // Then
        assertEquals(
                "Moscow Branch" + System.lineSeparator() +
                        "============" + System.lineSeparator() +
                        "Vasya Puplin" + System.lineSeparator() +
                        "------------" + System.lineSeparator() +
                        "- account #1: 10.0" + System.lineSeparator(),
                report);
    }

    @Test
    void shouldGetBranchReportWithEmptyMoneyAmount() {
        // Given
        when(branchRepositoryDouble.getBranchById(1)).thenReturn(branchStub);
        when(branchStub.getName()).thenReturn("Moscow Branch");
        when(branchStub.getBranchClients()).thenReturn(singletonList(clientStub));
        when(clientStub.getName()).thenReturn("Vasya Puplin");
        when(clientStub.getAccounts()).thenReturn(singletonList(accountStub));
        when(accountStub.getAmount()).thenReturn(0.0);
        when(accountStub.getId()).thenReturn(1);

        // When
        final String report = reportingSut.getReport(1);

        // Then
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
        // Given
        when(clientStub.getAccounts()).thenReturn(singletonList(accountStub));
        when(clientStub.getName()).thenReturn("Vasya Puplin");
        when(accountStub.getAmount()).thenReturn(10.0);
        when(accountStub.getId()).thenReturn(1);

        // When
        String report = reportingSut.getReport(clientStub);

        // Then
        assertEquals(
                "Vasya Puplin" + System.lineSeparator() +
                        "------------" + System.lineSeparator() +
                        "- account #1: 10.0" + System.lineSeparator(),
                report);
    }

    @Test
    void shouldGetReportForClientWithEmptyMoneyAmount() {
        // Given
        when(clientStub.getAccounts()).thenReturn(singletonList(accountStub));
        when(clientStub.getName()).thenReturn("Vasya Puplin");
        when(accountStub.getAmount()).thenReturn(0.0);
        when(accountStub.getId()).thenReturn(1);

        // When
        String report = reportingSut.getReport(clientStub);

        // Then
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