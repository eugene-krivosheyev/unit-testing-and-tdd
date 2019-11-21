package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class SavingAccountTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    //todo TestNameRule

    @Test
    public void shouldExistAndInitializedSavingAccountWhenCreatedWithCorrectArgumentsAndPositiveAmount() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = mock(Client.class);
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
        Client stubClient = mock(Client.class);
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
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");

        UUID dummyId = null;
        Client stubClient = mock(Client.class);
        double stubAmount = 1;

        //endregion

        //region when
        new SavingAccount(dummyId, stubClient, stubAmount);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("client is null");

        UUID stubId = UUID.randomUUID();
        Client dummyClient = null;
        double stubAmount = 1;
        //endregion


        //region when
        new SavingAccount(stubId, dummyClient, stubAmount);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountLessThanZero() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("amount less than 0");

        UUID stubId = UUID.randomUUID();
        Client stubClient = mock(Client.class);
        double stubAmount = -1;
        //endregion


        //region when
        new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        //endregion
    }

    @Test
    /* Integration test
     */
    public void shouldAddIdToClientAccountIdsWhenSavingAccountCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = mock(Client.class);
        double stubAmount = 0;
        //endregion

        //region when
        final SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        verify(stubClient, times(1)).addIdToClientAccountIds(sut);
        //endregion
    }

    @Test
    public void shouldAddIdToClientAccountIdsWhenSavingAccountCreatedWithMock() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = mock(Client.class);
        double stubAmount = 0;
        //endregion

        //region when
        final SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        verify(stubClient, times(1)).addIdToClientAccountIds(sut);
        //endregion
    }
}
