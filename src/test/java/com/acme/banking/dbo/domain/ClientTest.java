package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

final class ClientTest {


    @Test
    void shouldBeCreated() {
        // Given
        String name = "Dummy";
        int id = 1;

        // When
        Client sut = new Client(id, name);

        // Then
        assertAll(
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(name, sut.getName())
        );
    }

    @Test
    void shouldNotBeCreatedDueToError() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Client(1, null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Client(1, ""))
        );
    }

    @Test
    void shouldStoreAccount() {
        // Given
        int id = 1;
        Client sut = new Client(id, "Dummy");

        int initialAccountSize = sut.getAccounts().size();
        assumeTrue(sut.getAccounts().isEmpty());

        // When
        sut.addAccount(new SavingAccount(id, sut, 1));

        // Then
        assertAll(
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(initialAccountSize + 1, sut.getAccounts().size())
        );
    }
}
