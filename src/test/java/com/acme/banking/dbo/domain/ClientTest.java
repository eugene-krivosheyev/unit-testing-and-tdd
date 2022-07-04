package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


@DisplayName("Test suite")
public class ClientTest {
    @Test
    @Disabled("temporary disabled")
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
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @Test
    public void shouldNotCreateWithZeroId() {
        //region given
        final int clientId = 0;
        final String clientName = "client name";

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));

    }

    @Test
    public void shouldNotCreateWithNegativeId() {
        //region given
        final int clientId = -1;
        final String clientName = "client name";

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldNotCreateWithBlankName() {
        //region given
        final int clientId = 1;
        final String clientName = " ";

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldNotCreateWithNullName() {
        //region given
        final int clientId = 1;
        final String clientName = null;

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldNotCreateWithEmptyName() {
        //region given
        final int clientId = 1;
        final String clientName = "";

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }
}
