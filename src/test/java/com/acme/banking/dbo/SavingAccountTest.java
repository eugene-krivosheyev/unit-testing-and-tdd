package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class SavingAccountTest {
    @Test
    public void shouldSaveIdWhenSavingAccountWithPositiveCase(){
        //region given
        UUID dummyUuid = UUID.randomUUID();
        Client dummyClient = new Client(dummyUuid, "Dummy");
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(dummyUuid,  dummyClient, 0.);
        //endregion

        //region then
        Assert.assertEquals(dummyUuid, sut.getId());
        //endregion
    }
}
