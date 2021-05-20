package com.acme.banking.dbo;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractTest {
    void assertThrowIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(
            final Executable producer,
            final String expectedMessage
    ) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, producer);
        assertEquals(expectedMessage, thrown.getMessage());
    }
}
