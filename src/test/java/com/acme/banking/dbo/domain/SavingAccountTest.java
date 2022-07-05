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
        double dummyAmount = 1;

        int dummyClientId = 1;
        String dummyClientName = "some name";
        Client dummyClient = new Client(dummyClientId, dummyClientName);

        assumeTrue(dummyClient.getId() == dummyClientId);
        assumeTrue(dummyClient.getName().equals(dummyClientName));
        //endregion

        //region when
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, dummyClient, dummyAmount));
        //endregion
    }

    @Test
    public void shouldGetErrorWhenClientIsNull() {
        //region given
        int dummyId = 1;
        double dummyAmount = 1;

        Client client = null;
        //endregion

        //region when
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(dummyId, client, dummyAmount));
        //endregion
    }

    @Test
    public void shouldCreateNewAccountWhenValidClientAndId() {
        //region given
        final int dummyClientId = 1;
        final String dummyClientName = "dummy client name";

        int id = 1;
        double dummyAmount = 1;
        Client dummyClient = new Client(dummyClientId, dummyClientName);

        assumeTrue(dummyClient.getId() == dummyClientId);
        assumeTrue(dummyClient.getName().equals(dummyClientName));
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(id, dummyClient, dummyAmount);
        //endregion

        //region then
        assertAll("Client store its properties",
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(dummyAmount, sut.getAmount()),
                () -> assertEquals(dummyClient, sut.getClient())
        );
    }
}