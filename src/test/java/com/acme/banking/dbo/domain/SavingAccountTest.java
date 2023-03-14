package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SavingAccountTest {
    private static final Client CLIENT = new Client(200, "Ivan");
    private static final double AMOUNT = 100.24;
    private static final int ACCOUNT_ID = 100;

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "0  |0",
            "1  |0",
            "1  |1"})
    void shouldCreateSavingAccountWhenValidParams(int accountId, double amount) {
        SavingAccount savingAccount = new SavingAccount(accountId, CLIENT, amount);

        Assertions.assertEquals(accountId, savingAccount.getId());
        Assertions.assertEquals(CLIENT.getId(), savingAccount.getClient().getId());
        Assertions.assertEquals(CLIENT.getName(), savingAccount.getClient().getName());
        Assertions.assertEquals(amount, savingAccount.getAmount());
    }

    @Test
    void shouldThrowWhenNegativeAccountId() {
        int accountId = -100;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(accountId, CLIENT, AMOUNT));
    }

    @Test
    void shouldThrowWhenClientIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ACCOUNT_ID, null, AMOUNT));
    }

    @Test
    void shouldThrowWhenNegativeAmount() {
        double amount = -100;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ACCOUNT_ID, CLIENT, amount));
    }
}