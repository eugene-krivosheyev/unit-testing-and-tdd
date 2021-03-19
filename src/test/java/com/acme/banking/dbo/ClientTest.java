package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    public static final String CLIENT_NAME = "dummy client name";

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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
                hasProperty("name", is(CLIENT_NAME))
        ));
        //endregion
    }

    @Test
    public void shouldExceptionWhenIdIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("id must not be null");
        new Client(null, CLIENT_NAME);
    }

    @Test
    public void shouldExceptionWhenNameIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("name must not be null");
        new Client(ID_STUB, null);
    }

    @Test
    public void shouldExceptionWhenNameIsEmpty() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("name must not be empty");
        new Client(ID_STUB, "");
    }

    @Test
    public void shouldReturnId() {
        Client client = new Client(ID_STUB, CLIENT_NAME);
        UUID sut = client.getId();

        assertEquals(ID_STUB, sut);
    }

    @Test
    public void shouldReturnName() {
        Client client = new Client(ID_STUB, CLIENT_NAME);
        String sut = client.getName();

        assertEquals(CLIENT_NAME, sut);
    }
}
