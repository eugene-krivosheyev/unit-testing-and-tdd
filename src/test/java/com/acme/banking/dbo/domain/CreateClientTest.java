package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static com.acme.banking.dbo.TestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CreateClientTest {

    @Test
    public void shouldNotCreateNewClientWhenIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Client(-1, VALID_CLIENT_NAME));
    }

    @Test
    public void shouldNotCreateNewClientWhenNameEmpty() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Client(VALID_ID_1, "")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Client(VALID_ID_1, null))
        );
    }

    @Test
    public void shouldCCreateNewClientSuccessfullyWhenArgumentsValid() {
        Client sut1 = CLIENT_SUT;
        Client sut2 = new Client(VALID_ID_0, VALID_CLIENT_NAME);

        assumeTrue(sut1 != null);
        assumeTrue(sut2 != null);

        assertAll(
                () -> assertEquals(VALID_ID_1, sut1.getId()),
                () -> assertEquals(VALID_CLIENT_NAME, sut1.getName()),
                () -> assertEquals(VALID_ID_0, sut2.getId())
        );
    }
}