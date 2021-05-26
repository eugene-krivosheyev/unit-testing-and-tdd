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

    @Test
    public void shouldNotCreatedWhenIdIsNegaitve(){
        int negativeId = -1;
        String dummyName = "Dummy name";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(negativeId, dummyName),
                "Expected IllegalArgumentException to be thrown if ClientId is less than 0.");
        assertEquals("Client ID is less than 0.", thrown.getMessage());
    }

    @Test
    public void shouldNotCreatedWhenNameIsNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(1, null),
                "Expected IllegalArgumentException to be thrown if ClientName is NULL.");
        assertEquals("Client Name is null.", thrown.getMessage());
    }

    @Test
    public void shouldNotCreatedWhenNameIsEmpty(){
        int dummyId = 1;
        String emptyName = "";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(dummyId, emptyName),
                "Expected IllegalArgumentException to be thrown if ClientName is empty.");
        assertEquals("Client Name is empty.", thrown.getMessage());
    }

    @Test
    public void shouldStorePropertiesWhenCreated(){
        int validId = 1;
        String validName = "Client name";
        Client sut = new Client(validId, validName);

        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(validId)),
                        hasProperty("name", notNullValue()),
                        hasProperty("name", equalTo(validName))
                ));
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    public void shouldNotCreatedWhenInvalidArgument(TestData argument){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(argument.getId(), argument.getName()),
                "Expected IllegalArgumentException to be thrown if ClientName is empty.");
        assertEquals(argument.getExceptionMessage(), thrown.getMessage());
    }

    static Stream<TestData> provideTestData(){
        return Stream.of(
                new TestData(-1, "Dummy name", "Client ID is less than 0."),
                new TestData(1, null, "Client Name is null."),
                new TestData(1, "", "Client Name is empty.")
        );
    }

    /*
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
    }*/
}

class TestData{
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
        return "Test case: id - " + id + ", name - " + name + ", message - " + exceptionMessage;
    }
}

