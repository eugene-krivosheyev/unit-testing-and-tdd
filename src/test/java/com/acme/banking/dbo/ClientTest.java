package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test suite")
class ClientTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenIdLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Client(-1, "name"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Client(1, null));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Client(1, "    "));
    }

    @ParameterizedTest
    @CsvSource({
            "0, name",
            "1, name"
    })
    void shouldCreateNewClientSuccessfully(int id, String name) {
        Client client = new Client(id, name);
        assertEquals(id, client.getId());
        assertEquals(name, client.getName());
    }

    @Test
    void shouldAddAccount() {
        var client = new Client(1, "name");

        var sut = new SavingAccount(0, client, 0d);

        assertEquals(client, sut.getClient());
        assertTrue(client.getAccounts().contains(sut));
    }

    @Test
    void shouldNotAddAccountWhenAccountOwnerIsOtherClient() {
        var client = new Client(1, "name");
        var otherClient = new Client(2, "other_name");
        var sut = new SavingAccount(0, client, 0d);

        assertThrows(IllegalArgumentException.class, () -> otherClient.addAccount(sut));
    }

    @Test
    void shouldNotAddAccountWhenAccountAlreadyExist() {
        var client = new Client(1, "name");
        var sut = new SavingAccount(0, client, 0d);

        assertThrows(IllegalArgumentException.class, () -> client.addAccount(sut));

    }
}
