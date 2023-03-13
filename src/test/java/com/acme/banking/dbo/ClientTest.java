package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.acme.banking.dbo.TestData.TEST_CLIENT_NAME;
import static com.acme.banking.dbo.TestData.TEST_CLIENT_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {
    @Test @Disabled("temporary disabled")
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
    public void shouldCreateNewClientWhenParametersAreCorrect() {
        Client sut = new Client(TEST_CLIENT_ID, TEST_CLIENT_NAME);

        assertNotNull(sut);
        assertEquals(TEST_CLIENT_ID, sut.getId());
        assertEquals(TEST_CLIENT_NAME, sut.getName());
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Client(-1, TEST_CLIENT_NAME));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Client(TEST_CLIENT_ID, ""));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Client(TEST_CLIENT_ID, null));
    }
}
