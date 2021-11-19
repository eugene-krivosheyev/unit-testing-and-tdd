package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
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
        assertAll(
                () -> assertThat(sut, hasProperty("accounts", hasSize(initialAccountSize + 1))),
                () -> assertThat(account, hasProperty("client", notNullValue())),
                () -> assertThat(account, hasProperty("client", equalTo(sut)))
        );
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
        assertAll(
                () -> assertThat(sut, hasProperty("accounts", empty())),
                () -> assertThat(account, hasProperty("client", nullValue()))
        );
    }

    @Test
    void shouldNotStoreAccountIfPresent() {
        // Given
        int id = 1;
        Client sut = new Client(id, "Dummy");
        assumeTrue(sut.getAccounts().isEmpty());

        Account account = new SavingAccount(id, sut, 1);
        sut.addAccount(account);

        int initialAccountSize = sut.getAccounts().size();

        // When
        sut.addAccount(account);

        // Then
        assertAll(
                () -> assertThat(sut, hasProperty("accounts", hasSize(initialAccountSize))),
                () -> assertThat(account, hasProperty("client", notNullValue())),
                () -> assertThat(account, hasProperty("client", equalTo(sut)))
        );
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
