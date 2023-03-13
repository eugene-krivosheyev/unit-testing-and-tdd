package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


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

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowWhenNullAndEmptyName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Client(100, name));
    }

    @Test
    void shouldThrowWhenNegativeId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Client(-100, "Ivan"));
    }

    @Test
    void shouldCreateClient() {
        Client client = new Client(100, "Ivan");

        Assertions.assertEquals("Ivan", client.getName());
        Assertions.assertEquals(100, client.getId());
    }
}
