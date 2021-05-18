package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest extends AbstractTest {
    @Test
    public void shouldNotBeCreatedWhenNegativeId() {
        // region Given
        final Executable producer = () -> new Client(-1, null);
        // endregion

        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, Client.ARG_EXCEPTION_MESSAGE_ID_NEGATIVE);
    }

    @Test
    public void shouldNotBeCreatedWhenNameIsNull() {
        // region Given
        final Executable producer = () -> new Client(1, null);
        // endregion

        // region Then
        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, Client.ARG_EXCEPTION_MESSAGE_NAME_NULL);
        // endregion
    }

    @Test
    public void shouldNotBeCreatedWhenNameIsEmpty() {
        // region Given
        final Executable producer = () -> new Client(1, "");
        // endregion

        // region Then
        throwIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, Client.ARG_EXCEPTION_MESSAGE_NAME_EMPTY);
        // endregion
    }

    @Test
    public void getFieldsWhenCreatedCorrectlyAndRequested() {
        // region Given
        final int expectedId = 1;
        final String expectedName = "Test Name";
        final Client client = new Client(expectedId, expectedName);
        // endregion

        // region When
        final int id = client.getId();
        final String name = client.getName();
        // endregion

        // region Then
        assertAll(
                () -> assertEquals(expectedId, id, "Getter for 'id' seems isn't working"),
                () -> assertEquals(expectedName, name, "Getter for 'name' seems isn't working")
        );
        // endregion
    }
}