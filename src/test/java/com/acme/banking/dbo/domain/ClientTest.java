package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;
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

        Account account = new SavingAccount(id, sut, 1);

        // When
        sut.addAccount(account);

        // Then
        allOf(
                hasProperty("accounts", hasSize(initialAccountSize + 1))
        ).matches(sut);

        allOf(
                hasProperty("client", notNullValue()),
                hasProperty("client", equalTo(sut))
        ).matches(account);
    }

    @Test
    void shouldRemoveAccount() {
        // Given
        int id = 1;
        Client sut = new Client(id, "Dummy");

        Account account = new SavingAccount(id, sut, 1);
        sut.addAccount(account);

        // When
        sut.removeAccount(account);

        // Then
        allOf(
                hasProperty("accounts", empty())
        ).matches(sut);

        allOf(
                hasProperty("client", nullValue())
        ).matches(account);
    }

    @Test
    void shouldNotStoreAccountDueToError() {
        // Given
        Client sut = new Client(1, "Dummy");

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> sut.addAccount(null)),
                () -> assertTrue(sut.getAccounts().isEmpty())
        );
    }
}
