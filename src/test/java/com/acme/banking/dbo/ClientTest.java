package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Test
    public void shouldNotCreateClientWhenIdIsIllegal() {
        int illegalId = 0;


        IllegalArgumentException sut = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Client(illegalId, "dummyClientName"));
        // AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut).hasMessageContaining("id is illegal");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void shouldNotCreateClientWhenNameIsEmptyOrNull(String client) {
        int dummyId = 1;

        IllegalArgumentException sut = Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(dummyId, client));
        // AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut).hasMessageContaining("name is empty or null");
    }

    @Test
    public void shouldCreateClientWhenPropertyValid() {
        int id = 1;
        String clientName = "name";

        assertDoesNotThrow(() -> new Client(id, clientName));
    }

    @Test
    public void shouldCreateClientAndGetPropsWhenPropsValid() {
        int id = 1;
        String clientName = "name";

        Client sut = new Client(id, clientName);

        assertAll("Client store its properties",
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
    }
}
