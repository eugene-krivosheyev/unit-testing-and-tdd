package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Before;
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
    private UUID stubId;
    private String dummy_client_name;
    private Client stubClient;

    @Before
    public void setUp() {
        stubId = UUID.randomUUID();
        dummy_client_name = "dummy client name";
        stubClient = mock(Client.class);
    }

    @Test
    public void shouldExistAndInitializedSavingAccountWhenCreatedWithCorrectArgumentsAndPositiveAmount() {
        //region given
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

        double stubAmount = 1;
        //endregion

        //region when
        new SavingAccount(null, stubClient, stubAmount);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("client is null");

        double stubAmount = 1;
        //endregion

        //region when
        new SavingAccount(stubId, null, stubAmount);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountLessThanZero() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("amount less than 0");

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
    /* Integration test
     */
    public void shouldAddIdToClientAccountIdsWhenSavingAccountCreatedWithBuilder() {
        //region given
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
