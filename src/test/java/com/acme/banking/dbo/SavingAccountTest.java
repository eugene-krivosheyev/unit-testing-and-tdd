package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class SavingAccountTest {
    @Rule
    public final TestName testName = new TestName();

    @Test
    public void shouldSavePropertiesWhenCreatedAndNotNullAndNotEmptyAllValuesAndPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID stubClientId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Collection<UUID> stubAccountsId = new ArrayList();

        Client stubClient = new Client(stubClientId, dummyName, stubAccountsId);
        double stubAmount = 10000.0;

        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertEquals(stubId, sut.getId());
        assertEquals(stubClient, sut.getClient());
        assertEquals(stubAmount, sut.getAmount(), 0.01);
        assertEquals(stubClientId, sut.getClientId());
        assertEquals("shouldSavePropertiesWhenCreatedAndNotNullAndNotEmptyAllValuesAndPositiveAmount", testName.getMethodName());
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldExceptionWhenCreatedAndNotNullAndNotEmptyAllValuesAndNotPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Collection<UUID> stubAccountsId = new ArrayList();
        Client dummyClient = new Client(stubId, dummyName, stubAccountsId);
        double stubAmount = -100.0;

        //region when
        SavingAccount sut = new SavingAccount(stubId, dummyClient, stubAmount);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldExceptionWhenCreatedAndNullClientAndNotNullIOtherAndPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Client dummyClient = null;
        double stubAmount = 10.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, dummyClient, stubAmount);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldExceptionWhenCreatedAndNotNullAndNotEmptyAllAndNotPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Collection<UUID> stubAccountsId = new ArrayList();
        Client dummyClient = new Client(stubId, dummyName, stubAccountsId);
        double stubAmount = -100.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, dummyClient, stubAmount);
        //endregion
    }

    @Test
    public void shouldExceptionWhenCreatedAndNotNullOtherAndPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID stubClientId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Collection<UUID> stubAccountsId = new ArrayList();
        Client stubClient = new Client(stubClientId, dummyName, stubAccountsId);
        double stubAmount = 0.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertEquals(stubId, sut.getId());
        assertEquals(stubClient, sut.getClient());
        assertEquals(stubAmount, sut.getAmount(), 0.01);
        assertEquals(stubClientId, sut.getClientId());
        assertEquals("shouldExceptionWhenCreatedAndNotNullOtherAndPositiveAmount", testName.getMethodName());
        //endregion
    }

}
