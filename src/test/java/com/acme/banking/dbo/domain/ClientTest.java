package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ClientTest {

    protected static int clientIdCounter = 0;
    protected static int accIdCounter = 10;
    protected static double amountCounter = 100.1;

    public Client makeClient() {
        clientIdCounter++;
        return new Client(clientIdCounter, "dummy client " + clientIdCounter);
    }

    public Account makeAccount(Client client) {
        amountCounter = amountCounter + 99.1;
        return new SavingAccount(accIdCounter, client, amountCounter);
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int clientId = 1;
        final String clientName = "dummy client name";

        Client c = new Client(clientId, clientName);
        assertAll("Client store its properties",
            () -> assertEquals(clientId, c.getId()),
            () -> assertEquals(clientName, c.getName())
        );
    }

    @Test
    public void shouldNotStoreBadPropertiesWhenCreated() {
        // TODO: remove duplicated code
        IllegalArgumentException e;

        e = assertThrows(IllegalArgumentException.class, () -> new Client(-1, "name"));
        assertEquals(Client.Err.BAD_ID, e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> new Client(1, ""));
        assertEquals(Client.Err.BAD_NAME, e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> new Client(1, null));
        assertEquals(Client.Err.BAD_NAME, e.getMessage());
    }

    @Test
    public void shouldAddAccountToEmptyAccountList() {
        Client client = makeClient();
        assertEquals(0, client.getAccounts().size());

        Account acc_1 = makeAccount(client);

        client.addAccount(acc_1);
        assertEquals(1, client.getAccounts().size());
        assertTrue(client.getAccounts().contains(acc_1));

        // Adding the same account must not change Collection<Account>
        client.addAccount(acc_1);
        assertEquals(1, client.getAccounts().size(), "The same account is added twice");
        assertTrue(client.getAccounts().contains(acc_1), "Adding the same account changes existing account");
    }

    @Test
    public void shouldNotAddAccountIfAccountIsAlreadyExists() {
        Client client_1 = makeClient();
        Account acc_1 = makeAccount(client_1);

        Client client_2 = makeClient();
        Account acc_2 = makeAccount(client_2);

        client_1.addAccount(acc_1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> client_1.addAccount(acc_2));
        assertEquals(Client.Err.CLIENT_DOESNT_MATCH, e.getMessage());
    }
}