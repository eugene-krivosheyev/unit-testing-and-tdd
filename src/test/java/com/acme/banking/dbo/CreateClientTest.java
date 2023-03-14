package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CreateClientTest {
    final int VALID_CLIENT_ID = 1;
    final String VALID_CLIENT_NAME = "John Doe";
    Client sut = new Client(VALID_CLIENT_ID, VALID_CLIENT_NAME);
    @Test
    public void shouldNotCreateNewClientWhenIdNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(-1, VALID_CLIENT_NAME));
    }

    @Test
    public void shouldNotCreateNewClientWhenNameEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(VALID_CLIENT_ID, ""));
    }

    @Test
    public void shouldCCreateNewClientSuccessfullyWhenArgumentsValid() {
        assumeTrue(sut != null);
        Assertions.assertEquals(VALID_CLIENT_ID, sut.getId());
        Assertions.assertEquals(VALID_CLIENT_NAME, sut.getName());
    }
}
