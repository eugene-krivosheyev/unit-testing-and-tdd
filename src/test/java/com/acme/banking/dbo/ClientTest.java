package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ClientTest {
    private static final UUID DUMMY_ID = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private static final String DUMMY_CLIENT_NAME = "dummy client name";
    private static Collection<? extends Account> accounts;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        Account account = mock(Account.class);
        accounts = Collections.singletonList(account);
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        Client sut = new Client(DUMMY_ID, DUMMY_CLIENT_NAME, accounts);

        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(DUMMY_ID)),
                        hasProperty("name", is(DUMMY_CLIENT_NAME)),
                        hasProperty("accounts", equalTo(accounts))
                ));
    }

    @Test
    public void shouldNotCreateInstanceWhenIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("id is null"));
        new Client(null, DUMMY_CLIENT_NAME, accounts);
    }

    @Test
    public void shouldNotCreateInstanceWhenNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("name is null"));
        new Client(DUMMY_ID, null, accounts);
    }

    @Test
    public void shouldNotCreateInstanceWhenNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("name is empty"));
        new Client(DUMMY_ID, "", accounts);
    }

    @Test
    public void shouldNotCreateInstanceWhenAccountsIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("accounts is null"));
        new Client(DUMMY_ID, DUMMY_CLIENT_NAME, null);
    }

    @Test
    public void shouldNotCreateInstanceWhenAccountsIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("accounts is empty"));
        new Client(DUMMY_ID, DUMMY_CLIENT_NAME, Collections.emptyList());
    }

}
