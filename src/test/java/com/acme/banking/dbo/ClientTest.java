package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
        /*
        assertAll("Client store its properties", // -> allOf
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
            anyOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", not(is(clientName)))
        ));
        */

        //AssertJ: хуже с anyOf + сложнее писать custom "matchers"
        org.assertj.core.api.Assertions.assertThat(sut) //allOf, AND
                .isNotNull().hasNoNullFieldsOrProperties() //not | OR
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void shouldNotCreatedWhenIncorrectData(TestData currentTestData) {
        final IllegalArgumentException error = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(currentTestData.getId(), currentTestData.getName()));

        assertEquals(currentTestData.getExceptionMessage(), error.getMessage());
    }

    static Stream<TestData> dataProvider() {
        return Stream.of(
                new TestData(-1, "dummy name", "id!"),
                new TestData(1, "", "name!"),
                new TestData(1, null, "name!")
        );
    }
}

class TestData {
    private int id;
    private String name;
    private String exceptionMessage;

    public TestData(int id, String name, String exceptionMessage) {
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

    @Override
    public String toString() {
        return "Test Case for id " + id;
    }
}
