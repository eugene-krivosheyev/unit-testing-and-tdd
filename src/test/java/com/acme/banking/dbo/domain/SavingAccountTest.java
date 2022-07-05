package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class SavingAccountTest {

    @Test
    public void shouldGetErrorWhenInvalidId() {
        //region given
        int id = 0;
        double amount = 1000;

        int clientId = 1;
        String clientName = "some name";
        Client client = new Client(clientId, clientName);

        assumeTrue(client.getId() == clientId);
        assumeTrue(client.getName().equals(clientName));
        //endregion

        //region when
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion
    }

    @Test
    public void shouldGetErrorWhenClientIsNull() {
        //region given
        int id = 0;
        double amount = 1000;

        Client client = null;
        //endregion

        //region when
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion
    }

    @Test
    public void shouldCreateNewAccountWhenValidClientAndId() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";

        int accountId = 1;
        double amount = 1000;
        Client client = new Client(clientId, clientName);

        assumeTrue(client.getId() == clientId);
        assumeTrue(client.getName().equals(clientName));
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accountId, client, amount);
        //endregion

        //region then
        assertAll("Client store its properties",
                () -> assertEquals(accountId, sut.getId()),
                () -> assertEquals(amount, sut.getAmount()),
                () -> assertEquals(client, sut.getClient())
        );
    }
}