package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Test
    public void shouldStorePropertiesWhenCreated() throws IllegalAccessException {
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
    public void GetErrorNullIdWhenCreated() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->new Client(null, "dummy client name"));
    }

    @Test
    public void GetErrorNullNameWhenCreated() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Client(ID_STUB, null));
    }

    @Test
    public void GetErrorEmptyNameWhenCreated() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Client(ID_STUB, ""));
    }
}
