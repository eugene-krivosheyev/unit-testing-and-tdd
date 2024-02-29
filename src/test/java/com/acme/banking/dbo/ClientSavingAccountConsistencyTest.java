package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientSavingAccountConsistencyTest {


    @Test
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

}
