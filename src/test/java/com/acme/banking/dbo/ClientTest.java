package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;



@DisplayName("Test suite")
public class ClientTest {
    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {

        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);

        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
/*
        //Hamcrest:
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", is(clientName))
        ));
*/
        //AssertJ:
        Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName)
        ;
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")

    public void NegativeTests(TestParams testParams) {
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                ()-> new Client(testParams.getId(), testParams.getName()));
        assertEquals(testParams.getExceptionMessage(), thrown.getMessage());
    }

     static Stream<TestParams> paramsProvider() {
        return Stream.of(
                new TestParams(-1, "dummy", "id!"),
                new TestParams(1, null, "name!"),
                new TestParams(1, "", "name!")
        );
    }
}

class TestParams {
    private int id;
    private String name;
    private String exceptionMessage;

    public TestParams (int id, String name, String exceptionMessage) {
        this.id = id;
        this.name = name;
        this.exceptionMessage = exceptionMessage;
    }

    public int getId () {
        return this.id;
    }

    public String getName () {
        return this.name;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
