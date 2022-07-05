package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


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

    @Disabled
    @ParameterizedTest
    @CsvSource({"0, dummy",
            "1, "})
    public void shouldNotCreateWhenParametersInvalid(int id, String name) {
        assertThrows(IllegalArgumentException.class, () -> new Client(id, name));
    }


    @Test
    public void shouldNotCreateWhenInvalidId() {
        int invalidId = 0;
        String name = "dummy";

        assertThrows(IllegalArgumentException.class, () -> new Client(invalidId, name));
    }

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        int dummyId = 1;
        String name = null;

        assertThrows(IllegalArgumentException.class, () -> new Client(dummyId, name));
    }
}
