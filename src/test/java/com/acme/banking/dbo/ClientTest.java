package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

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

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        Client sut = new Client(ID_STUB, "dummy client name");
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
    public void shouldNotNullIdWhenCreateClient() {
        //region Given
        UUID dummy = null;
        //endregion

        //region When
        Client sut;
        //endregion

        //region Then
        try {
            sut = new Client(dummy, "Jhon Doe");
            assertFalse("SUT not null", true);
        } catch (IllegalArgumentException ex) {
            assertEquals("id", ex.getMessage());
        }
        //endregion
    }

    @Test
    public void shouldNotNullNameWhenCreateClient() {
        //region Given
        UUID id = ID_STUB;
        String name = null;
        //endregion

        //region When
        Client sut;
        //endregion

        //region Then
        try {
            sut =new Client(id, name);
            assertFalse("SUT not null", true);
        } catch (IllegalArgumentException ex) {
            assertEquals("name", ex.getMessage());
        }
        //endregion
    }

    @Test
    public void shouldNotEmptyNameWhenCreateClient() {
        //region Given
        UUID id = ID_STUB;
        String name = "";
        //endregion

        //region When
        Client sut;
        //endregion

        //region Then
        try {
            sut =new Client(id, name);
            assertFalse("SUT not null", true);
        } catch (IllegalArgumentException ex) {
            assertEquals("name", ex.getMessage());
        }
        //endregion
    }

    @Test
    public void shouldReturnSameIdWhenCreateClient() {
        //region Given
        Client sut = new Client(ID_STUB, "Jhon Doe");
        //endregion

        //region When
        UUID sutID = sut.getId();
        //endregion

        //region Then
        assertEquals(ID_STUB, sutID);
        //endregion
    }
}
