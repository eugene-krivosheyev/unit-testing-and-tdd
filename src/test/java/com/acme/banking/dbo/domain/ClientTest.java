package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

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
    public void shouldThrowIllegalArgumentExceptionForIdWhenPassNullId() {
        Exception exception = null;
        try {
            new Client(null, "Some client name");
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertEquals("Id is null", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNameWhenPassNullName() {
        Exception exception = null;
        try {
            new Client(UUID.randomUUID(), null);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertEquals("Name is null", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNameWhenPassBlankName() {
        Exception exception = null;
        try {
            new Client(UUID.randomUUID(), "   ");
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        Assert.assertEquals("Name is blank", exception.getMessage());
    }

    @Test
    public void shouldCreateClientWhenPassValidIdAndName() {
        UUID uuid = UUID.randomUUID();
        String name = "Some client name";

        Client sut = new Client(uuid, name);

        Assert.assertEquals(uuid, sut.getId());
        Assert.assertEquals(name, sut.getName());
    }
}
