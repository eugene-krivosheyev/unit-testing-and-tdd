package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class SavingAccountTest {
    @Test
    public void shouldCreateNewAccountWhenClientIsNotNull() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";

        int accountId = 1;
        double amount = 1000;
        Client client = new Client(clientId, clientName);

        assumeTrue(client.getId() == clientId);
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accountId, client, amount);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(accountId, sut.getId()),
                () -> assertEquals(amount, sut.getAmount()),
                () -> assertEquals(client, sut.getClient())
        );

    }

}