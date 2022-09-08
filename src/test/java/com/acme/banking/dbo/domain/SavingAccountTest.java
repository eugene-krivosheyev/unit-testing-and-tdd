package com.acme.banking.dbo.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class SavingAccountTest {

    private final Client correctClient = new Client(10, "name");

    @Test
    void constructor_whenArgsValid_thenCreateNewSavingAccount() {
        assertDoesNotThrow(() -> new SavingAccount(10, correctClient, 1000.0));
    }

    @Test
    void constructor_whenArgsIsValid_thenCreateNewSavingAcc() {

        SavingAccount actual = new SavingAccount(10, correctClient, 1000.0);

        assertAll(
            () -> assertEquals(10, actual.getId()),
            () -> assertEquals(1000, actual.getAmount()),
            () -> assertEquals(correctClient, actual.getClient())
        );
    }

    @Test
    void constructor_whenClientIsNull_thenThrow() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
            () -> new SavingAccount(10, null, 1000.0));

        assertThat(actual,
            allOf(
                hasToString("java.lang.IllegalArgumentException: Client should be exist"),
                instanceOf(IllegalArgumentException.class),
                instanceOf(RuntimeException.class),
                hasProperty("message", equalTo("Client should be exist"))
            )
        );
    }

    @Test
    void constructor_whenAmountIsWrong_thenThrow() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
            () -> new SavingAccount(10, correctClient, -1000.0));

        org.assertj.core.api.Assertions.assertThat(actual)
            .hasToString("java.lang.IllegalArgumentException: Amount is not negative meaning")
            .hasFieldOrPropertyWithValue("message", "Amount is not negative meaning");

        org.assertj.core.api.Assertions.assertThat(actual.getMessage())
            .asInstanceOf(InstanceOfAssertFactories.STRING)
            .isNotNull()
            .isNotEmpty()
            .isEqualTo("Amount is not negative meaning");
    }
}