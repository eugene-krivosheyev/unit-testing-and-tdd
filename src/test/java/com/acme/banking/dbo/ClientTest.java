package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrownIllegalArgumentExceptionWhenUUIDNull() {
        Client sut = new Client(null, "dummy client name");
    }

    @Test
    public void shouldThrownIllegalArgumentExceptionWhenNameNull() {
        assertThatThrownBy(() -> {
            Client sut = new Client(ID_STUB, null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name must be not null or empty");
    }

    @Test
    public void shouldThrownIllegalArgumentExceptionWhenNameIsEmpty() {
        assertThatThrownBy(() -> {
            Client sut = new Client(ID_STUB, "");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name must be not null or empty1");
    }
}
