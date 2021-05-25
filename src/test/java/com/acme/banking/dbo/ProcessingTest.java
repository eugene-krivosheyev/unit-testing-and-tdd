package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    public void shouldClientSavedWhenCreateClient() {
        Client clientInfoToSave = mock(Client.class);
        when(clientInfoToSave.getName()).thenReturn("name");

        Client savedClient = mock(Client.class);
        when(savedClient.getName()).thenReturn("name");
        when(savedClient.getId()).thenReturn(1);

        ClientRepository clientRepoStub = mock(ClientRepository.class);
//        when(clientRepoStub.save(any(Client.class))).thenReturn(savedClient);
        when(clientRepoStub.save(clientInfoToSave)).thenReturn(savedClient);
//                .thenReturn(null);

        final Processing sut = new Processing(clientRepoStub);

//        assertNotEquals(0, sut.createClient().getId());
        final Client createdClient = sut.createClient(clientInfoToSave);

        assertNotNull(createdClient);
        assertThat(createdClient.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldNotCreateClientWhenClientInfoIsNull() {
        ClientRepository dummyRepo = mock(ClientRepository.class);
        final Processing sut = new Processing(dummyRepo);
        assertThrows(IllegalArgumentException.class,
                () -> sut.createClient(null));
    }

    @Test
    public void shouldNotCreateClientWhenClientsNameIsEmpty() {
        ClientRepository dummyRepo = mock(ClientRepository.class);
        final Processing sut = new Processing(dummyRepo);

        Client clientInfo = mock(Client.class);
        when(clientInfo.getName()).thenReturn("");

        assertThrows(IllegalArgumentException.class,
                () -> sut.createClient(clientInfo));
    }

    @Test
    public void shouldSaveUpdatedAccountsWhenValidTransfer() {
        ClientRepository accountsRepoMock = mock(ClientRepository.class);
        final Processing sut = new Processing(accountsRepoMock);

        sut.transfer(1, 2, 100);

        verify(accountsRepoMock, times(1)).findById(1);
        verify(accountsRepoMock, atLeastOnce()).findById(2);

        verify(accountsRepoMock).update(any(Account.class)); //https://stackoverflow.com/questions/1142837/verify-object-attribute-value-with-mockito
    }
}
