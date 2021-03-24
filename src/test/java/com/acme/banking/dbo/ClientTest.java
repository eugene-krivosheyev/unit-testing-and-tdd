package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Test suite")
public class ClientTest {
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
    public void shouldGetErrorWhenClientIdIsZero() {
        final int clientId = 0;
        final String clientName = "dummy client name";

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldGetErrorWhenNameIsNull() {
        final int clientId = 1;
        final String clientName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldGetErrorWhenNameIsEmpty() {
        final int clientId = 1;
        final String clientName = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldGetErrorWhenNameIsEmptyWithSpace() {
        final int clientId = 1;
        final String clientName = "    ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }
}
