package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.acme.banking.dbo.TestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CreateSavingAccountTest {

    private static Stream<Arguments> successAccountArguments() {
        return Stream.of(
                Arguments.of(
                        VALID_ID_1, CLIENT_SUT, CORRECT_AMOUNT_1, "CREATE ACCOUNT WITH ID = 1"
                ),
                Arguments.of(
                        VALID_ID_0, CLIENT_SUT, CORRECT_AMOUNT_0, "CREATE ACCOUNT WITH ID = 0"
                )
        );
    }

    @Test
    public void shouldNotCreateNewAccountWhenIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(INCORRECT_ID, CLIENT_SUT, CORRECT_AMOUNT_1));
    }

    @Test
    public void shouldNotCreateNewAccountWhenAmountNegative() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ID_1, CLIENT_SUT, INCORRECT_AMOUNT));
    }

    @Test
    public void shouldNotCreateNewAccountWhenClientNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ID_0, null, CORRECT_AMOUNT_0));
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("successAccountArguments")
    public void shouldCreateNewAccountSuccessfullyWhenArgumentsValid(
            int id,
            Client client,
            double amount,
            String testName
    ) {
        SavingAccount sut = assertDoesNotThrow(() -> new SavingAccount(id, client, amount));

        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("amount", amount)
                .hasFieldOrPropertyWithValue("client", client);
    }

    @Test
    public void shouldAddNewAccountIntoClientAccountsWhenAccountCreated() {
        SavingAccount sut = new SavingAccount(VALID_ID_1, CLIENT_SUT, CORRECT_AMOUNT_1);

        assertAll(
                () -> assertTrue(CLIENT_SUT.getAccounts().contains(sut))
        );
    }

    @Test
    public void shouldThrowWhenAccountBelongsAnotherClient() {
        SavingAccount sut = new SavingAccount(VALID_ID_1, CLIENT_SUT, CORRECT_AMOUNT_1);
        Client client = new Client(VALID_ID_0, VALID_CLIENT_NAME);

        assertThrows(IllegalArgumentException.class, () -> client.addAccount(sut));
    }

}
