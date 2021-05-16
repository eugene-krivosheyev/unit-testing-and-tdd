package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest extends AbstractTest {
    @Test
    void shouldNotBeCreatedWhenNegativeId() {
        // region Given
        final Executable producer = () -> new SavingAccount(-1, new Client(1, "Name"), 1.0);
        // endregion

        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, SavingAccount.ARG_EXCEPTION_MESSAGE_ID_NEGATIVE);
    }

    @Test
    void shouldNotBeCreatedWhenClientIsNull() {
        // region Given
        final Executable producer = () -> new SavingAccount(1, null, 1.0);
        // endregion

        // region Then
        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, SavingAccount.ARG_EXCEPTION_MESSAGE_CLIENT_NULL);
        // endregion
    }

    @Test
    void shouldNotBeCreatedWhenNameIsEmpty() {
        // region Given
        final Executable producer = () -> new SavingAccount(1, new Client(1, "Name"), -1.0);
        // endregion

        // region Then
        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, SavingAccount.ARG_EXCEPTION_MESSAGE_AMOUNT_NEGATIVE);
        // endregion
    }

    @Test
    void getAmountWhenCreatedCorrectlyAndRequested() {
        // region Given
        final Client client = new Client(1, "Test Name");
        final SavingAccount savingAccount = new SavingAccount(1, client, 1.0);
        // endregion

        // region When
        final double amount = savingAccount.getAmount();
        // endregion

        // region Then
        assertEquals(1.0, amount, "Getter for 'amount' seems isn't working");
        // endregion
    }

    @Test
    void getIdWhenCreatedCorrectlyAndRequested() {
        // region Given
        final Client client = new Client(1, "Test Name");
        final SavingAccount savingAccount = new SavingAccount(1, client, 1.0);
        // endregion

        // region When
        final int id = savingAccount.getId();
        // endregion

        // region Then
        assertEquals(1, id, "Getter for 'id' seems isn't working");
        // endregion
    }

    @Test
    void getClientWhenCreatedCorrectlyAndRequested() {
        // region Given
        final Client client = new Client(1, "Test Name");
        final SavingAccount savingAccount = new SavingAccount(1, client, 1.0);
        // endregion

        // region When
        final Client actualClient = savingAccount.getClient();
        // endregion

        // region Then
        assertSame(client, actualClient, "Getter for 'client' seems isn't working");
        // endregion
    }
}