package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class SavingAccountTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    //todo TestNameRule

    @Test
    public void shouldExistAndInitializedSavingAccountWhenCreatedWithCorrectArgumentsAndPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(UUID.randomUUID(), "some name");
        double stubAmount = 1;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertNotNull(sut);
        assertEquals(stubId, sut.getId());
        assertEquals(stubClient, sut.getClient());
        assertEquals(stubAmount, sut.getAmount(), 0.00001); //todo Need to approve delta with business analytics
        //endregion
    }

    @Test
    public void shouldExistAndInitializedSavingAccountWhenCreatedWithCorrectArgumentsAndZeroAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(UUID.randomUUID(), "some name");
        double stubAmount = 0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertNotNull(sut);
        assertEquals(stubId, sut.getId());
        assertEquals(stubClient, sut.getClient());
        assertEquals(stubAmount, sut.getAmount(), 0.00001); //todo Need to approve delta with business analytics
        //endregion
    }

    @Test
    @Ignore
    public void shouldThrowErrorWhenGetClientIdIsNull() {
        //region given
        UUID stubId = UUID.randomUUID();
        //todo mock client for client.getId ==null
        Client stubClient = new Client(UUID.randomUUID(), "some name");
        double stubAmount = 0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertNotNull(sut);
        assertEquals(stubId, sut.getId());
        assertEquals(stubClient, sut.getClient());
        assertEquals(stubAmount, sut.getAmount(), 0.00001); //todo Need to approve delta with business analytics
        //endregion
    }


    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        //todo добавить в шаблоны заполнение регионов
        //region given
        UUID stubId = null;
        Client stubClient = new Client(UUID.randomUUID(), "some name");
        double stubAmount = 1;

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");
        //endregion

        //region when
        new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = null;
        double stubAmount = 1;
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("client is null");
        //endregion


        //region when
        new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountLessThanZero() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "some name");
        double stubAmount = -1;
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("amount less than 0");
        //endregion


        //region when
        new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldAddIdToClientAccountIdsWhenSavingAccountCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        //todo mock client for client.getId ==null
        Client stubClient = new Client(UUID.randomUUID(), "some name");
        double stubAmount = 0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertTrue(stubClient.getAccountByIds().contains(sut));
        //endregion
    }
}
