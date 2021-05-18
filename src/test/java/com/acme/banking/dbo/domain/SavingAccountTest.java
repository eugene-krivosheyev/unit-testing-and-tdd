package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest extends AbstractTest {
    @Test
    public void shouldNotBeCreatedWhenNegativeId() {
        // region Given
        final Executable producer = () -> new SavingAccount(-1, new Client(1, "Name"), 1.0);
        // endregion

        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, SavingAccount.ARG_EXCEPTION_MESSAGE_ID_NEGATIVE);
    }

    @Test
    public void shouldNotBeCreatedWhenClientIsNull() {
        // region Given
        final Executable producer = () -> new SavingAccount(1, null, 1.0);
        // endregion

        // region Then
        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, SavingAccount.ARG_EXCEPTION_MESSAGE_CLIENT_NULL);
        // endregion
    }

    @Test
    public void shouldNotBeCreatedWhenNameIsEmpty() {
        // region Given
        final Executable producer = () -> new SavingAccount(1, new Client(1, "Name"), -1.0);
        // endregion

        // region Then
        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, SavingAccount.ARG_EXCEPTION_MESSAGE_AMOUNT_NEGATIVE);
        // endregion
    }

    @Test
    public void getFieldsWhenCreatedCorrectlyAndRequested() {
        // region Given
        final int expectedId = 1;
        final double expectedAmount = 1.0;
        final Client client = new Client(1, "Test Name");
        final Account account = new SavingAccount(expectedId, client, expectedAmount);
        // endregion

        // region When
        final double amount = account.getAmount();
        final Client actualClient = account.getClient();
        final int id = account.getId();
        // endregion

        // region Then
        assertAll(
                () -> assertEquals(expectedId, id, "Getter for 'id' seems isn't working"),
                () -> assertSame(client, actualClient, "Getter for 'client' seems isn't working"),
                () -> assertEquals(expectedAmount, amount, "Getter for 'amount' seems isn't working")
        );
        // endregion
    }
}