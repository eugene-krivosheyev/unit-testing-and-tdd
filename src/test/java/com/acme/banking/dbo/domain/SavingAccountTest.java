package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    @Test
    void shouldSaveAccount() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        final Client client = new Client(clientId, clientName);
        final double amount = 12;
        final int id = 3;
        //endregion

        //region then
        SavingAccount sut = new SavingAccount(id, client, amount);
        assertNotNull(sut);
        //endregion
    }

    @Test
    void shouldntSaveAccountWithZeroId() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        final Client client = new Client(clientId, clientName);
        final double amount = 12;
        final int id = 0;
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion
    }

    @Test
    void shouldntSaveAccountWithNegativeId() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        final Client client = new Client(clientId, clientName);
        final double amount = 12;
        final int id = -3;
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion
    }

    @Test
    void shouldntSaveAccountWithNullClient() {
        //region given
        final Client client = null;
        final double amount = 12;
        final int id = 1;
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion
    }

    @Test
    void shouldntSaveAccountWithNegativeAmount() {
        //region given
        final int clientId = 1;
        final String clientName = "client name";
        final Client client = new Client(clientId, clientName);
        final double amount = -10;
        final int id = 1;
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion
    }

    @Test
    void shouldntSaveAccountWithZeroAccount() {
        //region given
        final int clientId = 1;
        final String clientName = "client name";
        final Client client = new Client(clientId, clientName);
        final double amount = 0;
        final int id = 1;
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion
    }
}
