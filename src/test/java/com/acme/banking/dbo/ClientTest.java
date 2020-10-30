package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.notification.StoppedByUserException;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;


/**
 * Test Case
 */
public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        Client sut = new ClientBuilder()
                .build(); //new | mock()
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", notNullValue()),
                        hasProperty("name", is("dummy")),
                        hasProperty("accounts", notNullValue())
                ));
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNullId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");

        //region Then
        Client sut = new ClientBuilder()
                .withId(null) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNullName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null");

        //region Then
        Client sut = new ClientBuilder()
                .withName(null) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenEmptyName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is empty");

        //region Then
        Client sut = new ClientBuilder()
                .withName("") //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenEmptyAccounts() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("accounts is null");

        //region Then
        Client sut = new ClientBuilder()
                .withAccounts(null) //accumulate
                .build(); //new | mock()
        //endregion
    }
}
