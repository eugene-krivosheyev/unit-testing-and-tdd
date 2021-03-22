package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {
    private final int ID_STUB = 1;
    private final String CLIENT_NAME = "Dummy";

    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                  () -> assertEquals(clientId, sut.getId()),
                  () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
                   allOf(
                           hasProperty("id", notNullValue()),
                           hasProperty("id", equalTo(clientId)),
                           hasProperty("name", is(clientName))
                   ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                                       .hasFieldOrPropertyWithValue("id", clientId)
                                       .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }

    @Test
    public void shouldGetErrorWhenNameIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> new Client(ID_STUB, null));
    }
    @Test
    public void shouldGetErrorWhenIdIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> new Client(-1, CLIENT_NAME));
    }
    @Test
    public void shouldGetErrorWhenIdIsZero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> new Client(0, CLIENT_NAME));
    }
    @Test
    public void shouldReturnId() {
        Client client = new Client(ID_STUB, CLIENT_NAME);
        int sut = client.getId();

        assertEquals(ID_STUB, sut);
    }

    @Test
    public void shouldReturnName() {
        Client client = new Client(ID_STUB, CLIENT_NAME);
        String sut = client.getName();

        assertEquals(CLIENT_NAME, sut);
    }
}