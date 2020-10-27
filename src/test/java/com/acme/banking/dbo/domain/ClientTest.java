package com.acme.banking.dbo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ClientTest {
    private static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private static final String CLIENT_NAME = "dummy client name";
    private static final String EMPTY_STRING = "";
    private static final List<Account> LIST_ACCOUNT = Collections.emptyList();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        Client sut = new Client(ID_STUB, CLIENT_NAME, LIST_ACCOUNT);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", is("dummy client name"))
                ));
        //endregion
    }

    @Test
    public void shouldNotCreateWhenIdNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("id can't be null");
        Client sut = new Client(null, CLIENT_NAME, LIST_ACCOUNT);
    }

    @Test
    public void shouldNotCreateWhenNameNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("name can't be null or blank");
        Client sut = new Client(ID_STUB, null, LIST_ACCOUNT);
    }

    @Test
    public void shouldNotCreateWhenNameIsBlank() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("name can't be null or blank");
        Client sut = new Client(ID_STUB, EMPTY_STRING, LIST_ACCOUNT);
    }

    @Test
    public void shouldNotCreateWhenAccountsNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("accounts can't be null");
        Client sut = new Client(ID_STUB, CLIENT_NAME, null);
    }

    @Test
    public void shouldGetId() {
        Client sut = new Client(ID_STUB, CLIENT_NAME, LIST_ACCOUNT);
        assertEquals(ID_STUB, sut.getId());
    }

    @Test
    public void shouldGetName() {
        Client sut = new Client(ID_STUB, CLIENT_NAME, LIST_ACCOUNT);
        assertEquals(CLIENT_NAME, sut.getName());
    }

    @Test
    public void shouldGetAccounts() {
        Client sut = new Client(ID_STUB, CLIENT_NAME, LIST_ACCOUNT);
        assertEquals(LIST_ACCOUNT, sut.getAccounts());
    }

}
