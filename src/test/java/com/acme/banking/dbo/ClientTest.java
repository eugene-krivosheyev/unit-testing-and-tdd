package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


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
    @DisplayName("Test case")
    public void shouldNotCreateWhenNegativeClientId() {
        final int negativeClientId = -1;
        final String clientName = "dummy client name";

        try {
            Client sut = new Client(negativeClientId, clientName);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(e.getMessage(), "id < 0");
        }
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenEmptyClientName() {
        final int clientId = 1;
        final String emptyClientName = "";

        try {
            Client sut = new Client(clientId, emptyClientName);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(e.getMessage(), "Bad name");
        }
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenNullClientName() {
        final int clientId = 1;

        try {
            Client sut = new Client(clientId, null);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(e.getMessage(), "Bad name");
        }
    }
}
