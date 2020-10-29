package com.acme.banking.dbo.domain;

import org.junit.Before;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTest {

    private static UUID ID_STUB;
    private static String CLIENT_NAME;
    private static String EMPTY_STRING;
    private static List<Account> LIST_ACCOUNT;

    @Before
    public void init() {
        ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
        CLIENT_NAME = "dummy client name";
        EMPTY_STRING = "";
        LIST_ACCOUNT = Collections.emptyList();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        Client sut = new ClientBuilder().build();
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
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setId(null).build());
        assertEquals("id can't be null", ex.getMessage());
    }

    @Test
    public void shouldNotCreateWhenNameNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setName(null).build());
        assertEquals("name can't be null or blank", ex.getMessage());
    }

    @Test
    public void shouldNotCreateWhenNameIsBlank() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setName(EMPTY_STRING).build());
        assertEquals("name can't be null or blank", ex.getMessage());
    }

    @Test
    public void shouldNotCreateWhenAccountsNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setAccounts(null).build());
        assertEquals("accounts can't be null", ex.getMessage());
    }

    @Test
    public void shouldGetId() {
        Client sut = new ClientBuilder().setId(ID_STUB).build();
        assertEquals(ID_STUB, sut.getId());
    }

    @Test
    public void shouldGetName() {
        Client sut = new ClientBuilder().setName(CLIENT_NAME).build();
        assertEquals(CLIENT_NAME, sut.getName());
    }

    @Test
    public void shouldGetAccounts() {
        Client sut = new ClientBuilder().setAccounts(LIST_ACCOUNT).build();
        assertEquals(LIST_ACCOUNT, sut.getAccounts());
    }

}
