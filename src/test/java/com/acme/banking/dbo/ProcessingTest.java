package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    public void shouldNotSaveClientWhenClientIsNull() {
        ClientRepository clientsRepositoryDummy = mock(ClientRepository.class);
        final Processing sut = new Processing(clientsRepositoryDummy);

        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sut.createClient(null)
        );
        assertEquals("client!", thrown.getMessage());
    }

    @Test
    public void shouldNotSaveClientWhenClientNameIsNull() {
        ClientRepository clientsRepositoryDummy = mock(ClientRepository.class);
        final Processing sut = new Processing(clientsRepositoryDummy);


        Client clientStub = mock(Client.class);
        when(clientStub.getName()).thenReturn(null);
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sut.createClient(clientStub)
        );
        assertEquals("name!", thrown.getMessage());
    }

    @Test
    public void shouldSaveClientWhenClientIsValid() {
        final String VALID_CLIENT_NAME = "valid expected client name";
        final Integer VALID_CLIENT_ID = 1;

        Client clientStub = mock(Client.class);
        when(clientStub.getName()).thenReturn(VALID_CLIENT_NAME);

        Client savedClientStub = mock(Client.class);
        when(savedClientStub.getName()).thenReturn(VALID_CLIENT_NAME);
        when(savedClientStub.getId()).thenReturn(VALID_CLIENT_ID);

        ClientRepository clientsRepositoryStub = mock(ClientRepository.class);
//        when(clientsRepositoryStub.save(any(Client.class))).thenReturn(clientStub)
        when(clientsRepositoryStub.save(clientStub)).thenReturn(savedClientStub);

        final Processing sut = new Processing(clientsRepositoryStub);


        final Client savedClient = sut.createClient(clientStub);

        assertNotNull(savedClient);
        assertEquals(VALID_CLIENT_NAME, savedClient.getName());
        assertEquals(VALID_CLIENT_ID, savedClient.getId());
    }

    @Test
    public void shouldTransferWhenAccountAreValid() {
        Account accountStub1 = mock(Account.class);
        Account accountStub2 = mock(Account.class);
        ClientRepository accountRepositoryMock = mock(ClientRepository.class);
        when(accountRepositoryMock.findById(1)).thenReturn(accountStub1);
        when(accountRepositoryMock.findById(2)).thenReturn(accountStub2);
        final Processing sut = new Processing(accountRepositoryMock);

        sut.transfer(1, 2, 100);

//        verify(accountRepositoryMock, atLeastOnce()).findById(anyInt());
        verify(accountRepositoryMock).findById(1);
        verify(accountRepositoryMock).findById(2);

//        verify(accountRepositoryMock).update(new SavingAccount(?,?,?));
        verify(accountRepositoryMock, times(2)).update(any(Account.class)); //https://stackoverflow.com/questions/1142837/verify-object-attribute-value-with-mockito
    }

//    Account accSpy = spy(new SavingAccount(???));
//    when(accSpy.getAmount()).thenReturn(0.);
//    verify(accSpy, atLeastOnce()).getId();
}
