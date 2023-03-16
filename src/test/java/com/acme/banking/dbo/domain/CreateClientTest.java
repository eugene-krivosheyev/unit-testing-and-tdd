package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.acme.banking.dbo.TestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateClientTest {

    private static Stream<Arguments> incorrectAddAccountArguments() {
        return Stream.of(
                Arguments.of(
                        new SavingAccount(VALID_ID_1, CLIENT_SUT, CORRECT_AMOUNT_1), "AccountBelongsAnotherClient"
                ),
                Arguments.of(
                        null, "AccountIsNull"
                )
        );
    }

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

    @ParameterizedTest(name = "{1}")
    @MethodSource("incorrectAddAccountArguments")
    public void shouldThrowWhenAddIncorrectAccount(Account account, String testName) {
        Client client = new Client(VALID_ID_0, VALID_CLIENT_NAME);

        assertThrows(IllegalStateException.class, () -> client.addAccount(account));
    }
}
