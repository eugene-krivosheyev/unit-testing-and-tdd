package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientSavingAccountConsistencyTest {


    @Test
    @DisplayName("Given client account should change client then set new client")
    void givenClientAccountShouldChangeClientThenSetNewClient() {

        var oldClient = new Client(99, "OldClient");
        var newClient = new Client(1, "NewClient");
        var account = new SavingAccount(1, oldClient, 100);

        account.setClient(newClient);

        assertAll(
            () -> assertEquals(0, oldClient.getAccounts().size()),
            () -> assertEquals(newClient, account.getClient()),
            () -> assertTrue(newClient.getAccounts().contains(account))
        );
    }

    @Test
    @DisplayName("Given old client should remove account from accounts list")
    void givenOldClientShouldRemoveAccountFromAccountsList() {

        var oldClient = new Client(99, "OldClient");
        var account = new SavingAccount(1, oldClient, 100);

        oldClient.removeAccount(account);

        assertAll(
            () -> assertEquals(0, oldClient.getAccounts().size()),
            () -> assertNotEquals(oldClient, account.getClient())
        );
    }

    @Test
    @DisplayName("Given old client should correct set already existed value")
    void givenOldClientShouldCorrectSetAlreadyExistedValue() {

        var oldClient = new Client(99, "OldClient");
        var account = new SavingAccount(1, oldClient, 100);

        account.setClient(oldClient);

        assertAll(
            () -> assertEquals(1, oldClient.getAccounts().size()),
            () -> assertEquals(oldClient, account.getClient())
        );
    }

    @Test
    @DisplayName("Given account should unlink client")
    void givenAccountShouldUnlinkClient() {

        var oldClient = new Client(99, "OldClient");
        var account = new SavingAccount(1, oldClient, 100);

        account.unlinkClient();

        assertAll(
            () -> assertEquals(0, oldClient.getAccounts().size()),
            () -> assertNotEquals(oldClient, account.getClient())
        );
    }


    @Test
    @DisplayName("Given account should throw npe")
    void givenAccountShouldThrowNPE() {

        var oldClient = new Client(99, "OldClient");
        var account = new SavingAccount(1, oldClient, 100);

        Executable actionSut = () -> account.setClient(null);

        assertThrows(NullPointerException.class, actionSut);

    }
}
