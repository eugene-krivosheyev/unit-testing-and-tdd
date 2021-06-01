package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private static String GLOBAL_RESOURCE;
    private ClientRepository clientRepositoryDoubler;

    @BeforeAll // + @AfterAll
    public static void setUpGlobalResource() {
        GLOBAL_RESOURCE = "!!!";
    }

    @BeforeEach // + @AfterEach
    public void setUpRepository() {
       clientRepositoryDoubler = mock(ClientRepository.class);
    }

    @Test
    public void shouldClientSavedWhenCreateValidClient() {
        Client clientInfoToSave = mock(Client.class);
        when(clientInfoToSave.getName()).thenReturn("name");

        Client savedClient = mock(Client.class);
        when(savedClient.getName()).thenReturn("name");
        when(savedClient.getId()).thenReturn(1);

//        when(clientRepoStub.save(any(Client.class))).thenReturn(savedClient);
        when(clientRepositoryDoubler.save(clientInfoToSave)).thenReturn(savedClient);
//                .thenReturn(null);


        /*
        clientRepositoryDoubler = MockitoClientRepoBuilder
            .withClient("name")
            .withClient(1, "name")
        .build();

        clientRepositoryDoubler =
            withClient(withName("name").withId(1).build())
            .withClient(withName("name1").build())
        .build();

         */

        final Processing sut = new Processing(clientRepositoryDoubler);

//        assertNotEquals(0, sut.createClient().getId());
        final Client createdClient = sut.createClient(clientInfoToSave);

        assertNotNull(createdClient);
        assertThat(createdClient.getId()).isGreaterThan(0);
        assertThat(createdClient.getName()).isEqualTo(clientInfoToSave.getName());
    }

    @Test
    public void shouldNotCreateClientWhenClientInfoIsNull() {
        final Processing sut = new Processing(clientRepositoryDoubler);
        assertThrows(IllegalArgumentException.class,
                () -> sut.createClient(null));
    }

    @Test
    public void shouldNotCreateClientWhenClientsNameIsEmpty() {
        final Processing sut = new Processing(clientRepositoryDoubler);

        Client clientInfo = mock(Client.class);
        when(clientInfo.getName()).thenReturn("");

        //Client clientInfo = ClientBuilder.withName("").build();

        assertThrows(IllegalArgumentException.class,
                () -> sut.createClient(clientInfo));
    }

    @Test
    public void shouldSaveUpdatedAccountsWhenValidTransfer() {
        Account accountDummy = mock(Account.class);
        when(clientRepositoryDoubler.findById(anyInt())).thenReturn(accountDummy);
        final Processing sut = new Processing(clientRepositoryDoubler);

        sut.transfer(1, 2, 100);

        verify(clientRepositoryDoubler, times(1)).findById(1); //any()
        verify(clientRepositoryDoubler, atLeastOnce()).findById(2);

        verify(clientRepositoryDoubler, times(2)).update(any(Account.class)); //https://stackoverflow.com/questions/1142837/verify-object-attribute-value-with-mockito
    }

//    Account accountSpy = spy(new SavingAccount(1, null, 0));
//    when(accountSpy.getId()).thenThrow(new RuntimeException());
//    verify(accountSpy).getId();
}
