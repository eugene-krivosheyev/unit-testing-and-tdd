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
        UUID dummyId = ID_STUB;
        String dummyName = "dummy";
        Collection<Account> dummyAccounts = Collections.EMPTY_LIST;

        Client sut = new ClientBuilder()
                .withId(dummyId) //accumulate
                .withName(dummyName) //accumulate
                .withAccounts(dummyAccounts) //accumulate
                .build(); //new | mock()
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", is(dummyName)),
                        hasProperty("accounts", notNullValue())
                ));
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNullId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");

        //region Given
        UUID id = null;
        String dummyName = "dummy";
        Collection<Account> dummyAccounts = Collections.EMPTY_LIST;
        //endregion

        //region When
        Client sut;
        //endregion

        //region Then
        sut = new ClientBuilder()
                .withId(id) //accumulate
                .withName(dummyName) //accumulate
                .withAccounts(dummyAccounts) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNullName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null");

        //region Given
        UUID dummyId = ID_STUB;
        String name = null;
        Collection<Account> dummyAccounts = Collections.EMPTY_LIST;
        //endregion

        //region When
        Client sut;
        //endregion

        //region Then
        sut = new ClientBuilder()
                .withId(dummyId) //accumulate
                .withName(name) //accumulate
                .withAccounts(dummyAccounts) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenEmptyName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is empty");

        //region Given
        UUID dummyId = ID_STUB;
        String name = "";
        Collection<Account> dummyAccounts = Collections.EMPTY_LIST;
        //endregion

        //region When
        Client sut;
        //endregion

        //region Then
        sut = new ClientBuilder()
                .withId(dummyId) //accumulate
                .withName(name) //accumulate
                .withAccounts(dummyAccounts) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenEmptyAccounts() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("accounts is null");

        //region Given
        UUID dummyId = ID_STUB;
        String dummyName = "dummy";
        Collection<Account> accounts = null;
        //endregion

        //region When
        Client sut;
        //endregion

        //region Then
        sut = new ClientBuilder()
                .withId(dummyId) //accumulate
                .withName(dummyName) //accumulate
                .withAccounts(accounts) //accumulate
                .build(); //new | mock()
        //endregion
    }
}
