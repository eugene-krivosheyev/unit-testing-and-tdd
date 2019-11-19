package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SavingAccountTest {
    @Test
    public void shouldSavePropertiesWhenCreatedAndNotNullIdAndNotEmptyClientAndPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID stubClientId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Client dummyClient = new Client(stubClientId, dummyName);
        double stubAmount = 10000.0;

        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, dummyClient, stubAmount);
        //endregion

        //region then
        assertEquals(stubId, sut.getId());
        assertEquals(dummyClient, sut.getClient());
        assertEquals(stubAmount, sut.getAmount(), 0.01);
        assertEquals(stubClientId, sut.getClientId());
        //endregion
    }

    @Test
    public void shouldSavePropertiesWhenCreatedAndNotNullIdAndNotEmptyClientAndNotPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Client dummyClient = new Client(stubId, dummyName);
        double stubAmount = -100.0;
        double expectedAmount = 0.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, dummyClient, stubAmount);
        //endregion

        //region then
        assertNull(sut.getId());
        assertNull(sut.getClient());
        assertEquals(expectedAmount, sut.getAmount(), 0.01);
        //endregion
    }

    @Test
    public void shouldSavePropertiesWhenCreatedAndNotNullIdAndNullClientAndPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Client dummyClient = null;
        double stubAmount = -100.0;
        double expectedAmount = 0.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, dummyClient, stubAmount);
        //endregion

        //region then
        assertNull(sut.getId());
        assertNull(sut.getClient());
        assertEquals(expectedAmount, sut.getAmount(), 0.01);
        //endregion
    }

}
