package com.acme.banking.dbo.domain;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    private static Stream<Arguments> shouldThrowExceptionWhenInvalidParams() {
        return Stream.of(
                Arguments.of(-1, new Client(1, "name"), 0.01),
                Arguments.of(0, new Client(1, "name"), -0.01),
                Arguments.of(0, null, 0)
        );
    }

    private static Stream<Arguments> shouldCreateWhenValidParams() {
        return Stream.of(
                Arguments.of(0, new Client(1, "name"), 0.01),
                Arguments.of(1, new Client(1, "name"), 0.01),
                Arguments.of(1, new Client(1, "name"), 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldThrowExceptionWhenInvalidParams(int id, Client client, double amount) {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
    }

    @ParameterizedTest
    @MethodSource
    void shouldCreateWhenValidParams(int id, Client client, double amount) {
        assertDoesNotThrow(() -> new SavingAccount(id, client, amount));
    }
}