package com.acme.banking.dbo.domain;

import java.util.stream.Stream;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @ParameterizedTest
    @MethodSource
    void shouldThrowExceptionWhenInvalidParams(int id, String name) {
        assertThrows(IllegalArgumentException.class, ()-> new Client(id, name));
    }

    private static Stream<Arguments> shouldThrowExceptionWhenInvalidParams() {
        return Stream.of(
                Arguments.of(-1, "1"),
                Arguments.of(0, ""),
                Arguments.of(0, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCreateWhenValidParams(int validId, String validName) {
        Client client = new Client(validId, validName);

        assertEquals(validId, client.getId());
        assertEquals(validName, client.getName());
    }

    private static Stream<Arguments> shouldCreateWhenValidParams() {
        return Stream.of(
                Arguments.of(0, "1"),
                Arguments.of(1, "12")
        );
    }
}
