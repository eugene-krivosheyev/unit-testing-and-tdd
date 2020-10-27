package com.acme.banking.dbo.domain;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ClientTest {
    private static final UUID ID_STUB = UUID.randomUUID();
    private static final String CLIENT_NAME = "dummy client name";
    private static final String EMPTY_STRING = "";
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        Client sut = new Client(ID_STUB, CLIENT_NAME);
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
        Client sut = new Client(null, CLIENT_NAME);
    }

    @Test
    public void shouldNotCreateWhenNameNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("name can't be null or blank");
        Client sut = new Client(ID_STUB, null);
    }
    
    @Test
    public void shouldNotCreateWhenNameIsBlank() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("name can't be null or blank");
        Client sut = new Client(ID_STUB, EMPTY_STRING);
    }

    @Test
    public void shouldGetId() {
        Client sut = new Client(ID_STUB, CLIENT_NAME);
        assertEquals(ID_STUB, sut.getId());
    }

    @Test
    public void shouldGetName() {
        Client sut = new Client(ID_STUB, CLIENT_NAME);
        assertEquals(CLIENT_NAME, sut.getName());
    }

}
