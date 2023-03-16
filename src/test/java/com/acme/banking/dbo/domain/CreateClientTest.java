package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.acme.banking.dbo.TestData.INCORRECT_ID;
import static com.acme.banking.dbo.TestData.VALID_CLIENT_NAME;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateClientTest {

    @Test
    public void shouldNotCreateNewClientWhenIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Client(INCORRECT_ID, VALID_CLIENT_NAME));
    }

    @ParameterizedTest(name = "{2}")
    @CsvSource({"1,,NULL_NAME", "1,'',EMPTY_NAME"})
    public void shouldNotCreateNewClientWhenNameIncorrect(int id, String name, String testName) {
        assertThrows(IllegalArgumentException.class, () -> new Client(id, name));
    }

    @ParameterizedTest
    @CsvSource({"1,dummyName", "0,dummyName"})
    public void shouldCCreateNewClientSuccessfullyWhenArgumentsValid(int id, String name) {
        Client sut = assertDoesNotThrow(() -> new Client(id, name));

        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("name", name);
    }
}
