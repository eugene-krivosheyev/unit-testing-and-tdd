package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("Client test")
public class ClientTest {


    @ParameterizedTest
    @MethodSource("getClientInputParams")
    public void verifyInputParams(ClientInputParams params) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(params.getId(), params.getName()));
        assertEquals(thrown.getMessage(), params.getExpectedExceptionMessage(), params.getExceptionMessage());
    }

    static Stream<ClientInputParams> getClientInputParams() {
        return Stream.of(
                new ClientInputParams("Name", -1, "id!", "shouldNotCreateClientWhenIdIsNegative"),
                new ClientInputParams(null, 1, "name!", "shouldNotCreateClientWhenNameIsNull"),
                new ClientInputParams("", 1, "name!", "shouldNotCreateClientWhenNameIsEmpty")
        );
    }

    @Test
    public void shouldStoreClientPropertiesWhenCreated() {
        int id = 1;
        String name = "Test Client";
        Client client = new Client(id, name);
        Assertions.assertThat(client)
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("name", name);
    }

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
        /*
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
        */
        //Hamcrest:
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(clientId)),
                        hasProperty("name", is(clientName))
                ));


        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
//                .isNotNull().hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }
}

class ClientInputParams {
    private String name;
    private int id;
    private String expectedExceptionMessage;
    private String exceptionMessage;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getExpectedExceptionMessage() {
        return expectedExceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public ClientInputParams(String name, int id, String expectedExceptionMessage, String exceptionMessage) {
        this.name = name;
        this.id = id;
        this.expectedExceptionMessage = expectedExceptionMessage;
        this.exceptionMessage = exceptionMessage;
    }
}
