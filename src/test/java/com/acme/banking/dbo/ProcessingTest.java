package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.*;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private static Connection sharedConnection;

    private ClientRepository clientsRepositoryDoubler;
    private Processing sut;

    @BeforeAll // + @AfterAll
    public static void setUpSharedState() {
        sharedConnection = null;
    }

    @BeforeEach // + @AfterEach
    public void setUpClientRepo() {
        this.clientsRepositoryDoubler = mock(ClientRepository.class);
        Client clientStub = mock(Client.class);
        when(clientStub.getName()).thenReturn(null);
        when(clientsRepositoryDoubler.findById(anyInt())).thenReturn(clientStub);

        repo = new RepositoryBuilder()
                .withClient(1, ...,...,....)
                .withClient(2, ...,...,....)
                .withClient()
        .build();

        repo cl = repoBuilder
                .withClient()
                    .withId(1)
                    .withName("")
                .withClient()
                    .withId(3)
                .build();


        this.sut = new Processing(clientsRepositoryDoubler);
    }

    @Test
    public void shouldNotSaveClientWhenClientIsNull() {
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sut.createClient(null)
        );
        assertEquals("client!", thrown.getMessage());
    }

    @Test
    public void shouldNotSaveClientWhenClientNameIsNull() {
        Client clientStub = mock(Client.class);
        when(clientStub.getName()).thenReturn(null);

        Client clientStub = ClientBuilder.withName(null).build();


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

//        when(clientsRepositoryStub.save(any(Client.class))).thenReturn(clientStub)
        when(clientsRepositoryDoubler.save(clientStub)).thenReturn(savedClientStub);

        clientsRepositoryDoubler = new DBRepoBuilder("jdbc://lfkgjdl")
                .withClient(ClientBuilder.withName().withId().build())
                .withClient(VALID_CLIENT_ID, VALID_CLIENT_NAME)
            .build();

        final Client savedClient = sut.createClient(clientStub);

        assertNotNull(savedClient);
        assertEquals(VALID_CLIENT_NAME, savedClient.getName());
        assertEquals(VALID_CLIENT_ID, savedClient.getId());
    }

    @Test
    public void shouldTransferWhenAccountAreValid() {
        Account accountStub1 = mock(Account.class);
        Account accountStub2 = mock(Account.class);
        when(this.clientsRepositoryDoubler.findById(1)).thenReturn(accountStub1);
        when(this.clientsRepositoryDoubler.findById(2)).thenReturn(accountStub2);

        sut.transfer(1, 2, 100);

//        verify(accountRepositoryMock, atLeastOnce()).findById(anyInt());
        verify(clientsRepositoryDoubler).findById(1);
        verify(clientsRepositoryDoubler).findById(2);

//        verify(accountRepositoryMock).update(new SavingAccount(?,?,?));
        verify(clientsRepositoryDoubler, times(2)).update(any(Account.class)); //https://stackoverflow.com/questions/1142837/verify-object-attribute-value-with-mockito
    }

//    Account accSpy = spy(new SavingAccount(???));
//    when(accSpy.getAmount()).thenReturn(0.);
//    verify(accSpy, atLeastOnce()).getId();
}
