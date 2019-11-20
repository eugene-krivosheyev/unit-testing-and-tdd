package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class SavingAccountTest {
    @Test
    public void shouldCreateNewSavingAccountWithValidValues() {
        //region given
        UUID dummyUuid = UUID.randomUUID();
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(dummyUuid,0.);
        //endregion

        //region then
        Assert.assertNotNull(sut);
        Assert.assertEquals(dummyUuid, sut.getId());
        Assert.assertEquals(dummyUuid, sut.getClientId());
        Assert.assertEquals(0, sut.getAmount(), 0.0);
        //endregion
    }
}
