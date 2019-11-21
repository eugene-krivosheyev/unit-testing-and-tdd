package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.ClientRepository;
import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    @Test
    public void shouldGetClientReportWhenNoAccounts() {
        ClientRepository clients = mock(ClientRepository.class);
        Client clientStub = mock(Client.class);
        UUID clientStubId = UUID.randomUUID();
        when(clientStub.getId()).thenReturn(clientStubId);
        when(clientStub.getName()).thenReturn("Name");
        when(clients.findById(clientStubId)).thenReturn(clientStub);
        Reporting sut = new Reporting(clients);

        String report = sut.getReport(clientStubId);

        assertThat(report).contains("## " + clientStubId + " Name");
    }

    @Test
    public void shouldGetClientReportWhenOneAccount() {
        Reporting sut = new Reporting();
        String report = sut.getReport(UUID.randomUUID());
        assertThat(report).contains("### 1 100.");
    }
}
