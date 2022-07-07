package com.acme.banking.dbo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.acme.banking.dbo.domain.ClientAccountTestHelper.DUMMY_ID;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.DUMMY_NAME;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.createClient;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
class ClientTest {
    @Test
    @Disabled("temporary disabled")
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int invalidClientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(invalidClientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(invalidClientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(invalidClientId)),
                        hasProperty("name", is(clientName))
                ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", invalidClientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @Test
    void shouldCreateClient() {
        Assertions.assertThat(createClient())
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("name", DUMMY_NAME)
                .hasFieldOrPropertyWithValue("id", DUMMY_ID);
    }

    @Test
    void shouldNotCreateClientWhenIdIsZero() {
        final int invalidClientId = 0;
        assertThatThrownBy(() -> {
            new Client(invalidClientId, DUMMY_NAME);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id is not positive");
    }

    @Test
    void shouldNotCreateClientWhenIdIsNegative() {
        final int invalidClientId = -1;

        assertThatThrownBy(() -> {
            new Client(invalidClientId, DUMMY_NAME);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id is not positive");
    }

    @Test
    void shouldNotCreateClientWhenStringIsNull() {
        final String clientName = "";

        assertThatThrownBy(() -> {
            new Client(DUMMY_ID, clientName);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id is not positive");

        assertThrows(IllegalArgumentException.class, () -> new Client(DUMMY_ID, clientName));
    }
}
