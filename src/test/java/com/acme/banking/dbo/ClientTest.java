package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest extends AbstractTest {
    @ParameterizedTest
    @MethodSource("guardClausesParams")
    public void shouldNotBeCreatedWhenNegativeId(final ClientTestParams params) {
        // region Given
        final Executable producer = () -> new Client(params.getId(), params.getName());
        // endregion

        assertThrowIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, params.getExceptionMessage());
    }

    private static Stream<ClientTestParams> guardClausesParams() {
        return Stream.of(
                new ClientTestParams(-1, null, "ID is expected to be positive int"),
                new ClientTestParams(1, null, "Name is NULL"),
                new ClientTestParams(1, "", "Name is empty")
        );
    }

    @Test
    public void getFieldsWhenCreatedCorrectlyAndRequested() {
        // region Given
        final int expectedId = 1;
        final String expectedName = "Test Name";
        final Client client = new Client(expectedId, expectedName);
        // endregion

        // region When
        final int id = client.getId();
        final String name = client.getName();
        // endregion

        // region Then
        assertAll(
                () -> assertEquals(expectedId, id, "Getter for 'id' seems isn't working"),
                () -> assertEquals(expectedName, name, "Getter for 'name' seems isn't working")
        );

        MatcherAssert.assertThat(client,
                Matchers.allOf(
                        Matchers.hasProperty("id", equalTo(expectedId)),
                        Matchers.hasProperty("name", equalTo(expectedName))
                )
        );

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(id).isEqualTo(expectedId);
        softly.assertThat(name).isEqualTo(expectedName);
        softly.assertAll();
        // endregion
    }

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
        //endregion
    }
}

class ClientTestParams {
    final private int id;
    final private String name;
    final private String exceptionMessage;

    public ClientTestParams(int id, String name, String exceptionMessage) {
        this.id = id;
        this.name = name;
        this.exceptionMessage = exceptionMessage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
