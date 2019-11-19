package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class SavingAccountTest {
    @Test
    public void shouldSavingIdWhenSavingAccountWithDefaultParams(){
        final UUID dummyUuid = new UUID(0L, 0L);
        final Client dummyClient = new Client(dummyUuid, "Dummy");

        final SavingAccount sut = new SavingAccount(dummyUuid,  dummyClient, 0.);


        Assert.assertEquals(sut.getId(), dummyUuid);
    }
}
