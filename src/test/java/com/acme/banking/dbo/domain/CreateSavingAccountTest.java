package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.acme.banking.dbo.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CreateSavingAccountTest {

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
        Client sutClient = new Client(VALID_ID_1, VALID_CLIENT_NAME);

        assumeTrue(sutClient.getAccounts().size() == 0);

        SavingAccount sutAccount = assertDoesNotThrow(() -> new SavingAccount(VALID_ID_1, sutClient, CORRECT_AMOUNT_1));

        assertThat(sutClient.getAccounts())
                .filteredOn(account -> account.equals(sutAccount))
                .filteredOn(account -> account.getClient().equals(sutClient))
                .hasSize(1)
        ;

    }

    @ParameterizedTest(name = "{1}")
    @MethodSource("incorrectAddAccountArguments")
    public void shouldThrowWhenAddIncorrectAccount(Account account, String testName) {
        Client client = new Client(VALID_ID_0, VALID_CLIENT_NAME);

        assertThrows(IllegalStateException.class, () -> client.addAccount(account));
    }

}
