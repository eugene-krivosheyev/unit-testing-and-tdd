package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavingAccountTest {
    private static final Client VALID_CLIENT = new Client(200, "Ivan");
    private static final double VALID_AMOUNT = 100.24;
    private static final int VALID_ACCOUNT_ID = 100;

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "0  |0",
            "1  |0",
            "1  |1"})
    void shouldCreateSavingAccountWhenValidParams(int validAccountId, double validAmount) {
        SavingAccount savingAccount = new SavingAccount(validAccountId, VALID_CLIENT, validAmount);

        assertThat(savingAccount)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id", validAccountId)
                .hasFieldOrPropertyWithValue("amount", savingAccount.getAmount());
        assertThat(savingAccount.getClient())
                .hasFieldOrPropertyWithValue("id", VALID_CLIENT.getId())
                .hasFieldOrPropertyWithValue("name", VALID_CLIENT.getName());
    }

    @Test
    void shouldThrowWhenNegativeAccountId() {
        int invalidAccountId = -1;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(invalidAccountId, VALID_CLIENT, VALID_AMOUNT));
    }

    @Test
    void shouldThrowWhenClientIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ACCOUNT_ID, null, VALID_AMOUNT));
    }

    @Test
    void shouldThrowWhenNegativeAmount() {
        double invalidAmount = -1;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ACCOUNT_ID, VALID_CLIENT, invalidAmount));
    }
}