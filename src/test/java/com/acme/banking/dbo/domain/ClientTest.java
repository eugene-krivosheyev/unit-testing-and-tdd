package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest extends AbstractTest {
    @Test
    void shouldNotBeCreatedWhenNegativeId() {
        // region Given
        final Executable producer = () -> new Client(-1, null);
        // endregion

        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, Client.ARG_EXCEPTION_MESSAGE_ID_NEGATIVE);
    }

    @Test
    void shouldNotBeCreatedWhenNameIsNull() {
        // region Given
        final Executable producer = () -> new Client(1, null);
        // endregion

        // region Then
        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, Client.ARG_EXCEPTION_MESSAGE_NAME_NULL);
        // endregion
    }

    @Test
    void shouldNotBeCreatedWhenNameIsEmpty() {
        // region Given
        final Executable producer = () -> new Client(1, "");
        // endregion

        // region Then
        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, Client.ARG_EXCEPTION_MESSAGE_NAME_EMPTY);
        // endregion
    }

    @Test
    void getIdWhenCreatedCorrectlyAndRequested() {
        // region Given
        final Client client = new Client(1, "Test Name");
        // endregion

        // region When
        final int id = client.getId();
        // endregion

        // region Then
        assertEquals(1, id, "Getter for 'id' seems isn't working");
        // endregion
    }

    @Test
    void getNameWhenCreatedCorrectlyAndRequested() {
        // region Given
        final Client client = new Client(1, "Test Name");
        // endregion

        // region When
        final String name = client.getName();
        // endregion

        // region Then
        assertEquals("Test Name", name, "Getter for 'name' seems isn't working");
        // endregion
    }
}