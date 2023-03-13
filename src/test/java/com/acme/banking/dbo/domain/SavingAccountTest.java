package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static com.acme.banking.dbo.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    @Test
    public void shouldCreateNewAccountWhenParametersAreCorrect() {
        Client stubClient = stubClient();

        SavingAccount sut = new SavingAccount(TEST_ACCOUNT_ID, stubClient, TEST_AMOUNT);

        assertNotNull(sut);
        assertEquals(TEST_AMOUNT, sut.getAmount());
        assertEquals(stubClient, sut.getClient());
        assertEquals(TEST_ACCOUNT_ID, sut.getId());
    }

    @Test
    public void shouldThrowExceptionWhenAccountIdIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(-1, stubClient(), TEST_AMOUNT));
    }

    @Test
    public void shouldThrowExceptionWhenClientIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(TEST_ACCOUNT_ID, null, TEST_AMOUNT));
    }

    @Test
    public void shouldThrowExceptionWhenAmountIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(TEST_ACCOUNT_ID, stubClient(), -5.0));
    }
}