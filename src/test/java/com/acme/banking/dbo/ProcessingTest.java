package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.*;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private static int state = 0;

    private AccountRepository dummyAccountRepo;
    private ClientRepository dummyClientRepo;
    private Processing sut;

    @BeforeClass
    public static void globalSetUp() {

    }

    @AfterClass
    public static void globalTearDown() {

    }

    @Before
    public void setUp() {
        dummyAccountRepo = mock(AccountRepository.class);
        dummyClientRepo = mock(ClientRepository.class);
        sut = new Processing(dummyAccountRepo, dummyClientRepo);
    }

    @After
    public void tearDown() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        sut.getAccountsByClientId(null);
    }

    @Test
    public void shouldGetAccountsByClientIdWhenClientAndAccountExist() {
        AccountRepository stubRepo = mock(AccountRepository.class);
        UUID clientId = UUID.randomUUID();

        /*
        new MockitoClientBuilder()
            .withId(UUID.fromString("vasya"))
            .withAccount(
                    new MockitoAccountBuilder()
                            .withAmount(1_000)
                        .build())
            .withAccount(
                    new MockitoAccountBuilder()
                            .withAmount(-1_000)
                        .build())
        .build();
        */

        Processing sut = new Processing(stubRepo, mock(ClientRepository.class));
        Collection<Account> accounts = sut.getAccountsByClientId(clientId);

        assertThat(accounts).hasSize(0);
    }

    @Test
    public void shouldRepositoryCreateClientWhenClientNameIsCorrect() {
        ClientRepository mockClientRepository = mock(ClientRepository.class);
        Processing sut = new Processing(
                mock(AccountRepository.class),
                mockClientRepository
        );

        sut.createClient("test name");

        verify(mockClientRepository, times(1))
                .createClient("test name");
    }
}
