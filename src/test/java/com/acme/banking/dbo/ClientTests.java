package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.UUID;

import static org.hamcrest.beans.HasPropertyWithValue.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientTests {
    private final UUID DUMMY_ID = UUID.randomUUID();
    private final String DUMMY_NAME = "DUMMY_NAME";
    private Client sut;

    @Mock
    public Collection<Account> mockAccounts;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewClientWhenValidArguments() {
        sut = ClientBuilder.builder()
                .withId(DUMMY_ID)
                .withName(DUMMY_NAME)
                .withAccounts(mockAccounts)
                .build();

        assertThat(
                sut,
                allOf(
                        hasProperty("id", equalTo(DUMMY_ID)),
                        hasProperty("name", equalTo(DUMMY_NAME)),
                        hasProperty("accounts", equalTo(mockAccounts))
                )
        );
    }

    @Test
    public void shouldNotCreateWhenClientWithoutId() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("ID is null");

        sut = ClientBuilder.builder()
                .withId(null)
                .withName(DUMMY_NAME)
                .withAccounts(mockAccounts)
                .build();
    }

    @Test
    public void shouldNotCreateWhenClientWithoutName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid name");

        sut = ClientBuilder.builder()
                .withId(DUMMY_ID)
                .withName(null)
                .withAccounts(mockAccounts)
                .build();
    }

    @Test
    public void shouldNotCreateWhenClientWithEmptyName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid name");

        sut = ClientBuilder.builder()
                .withId(DUMMY_ID)
                .withName("")
                .withAccounts(mockAccounts)
                .build();
    }

    @Test
    public void shouldNotCreateWhenClientWithoutAccounts() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Accounts is null");

        sut = ClientBuilder.builder()
                .withId(DUMMY_ID)
                .withName(DUMMY_NAME)
                .withAccounts(null)
                .build();
    }

    @Test
    public void shouldNotCreateWhenClientWithEmptyAccounts() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Accounts is empty");

        Mockito.when(mockAccounts.isEmpty()).thenReturn(true);

        sut = ClientBuilder.builder()
                .withId(DUMMY_ID)
                .withName(DUMMY_NAME)
                .withAccounts(mockAccounts)
                .build();
    }
}
