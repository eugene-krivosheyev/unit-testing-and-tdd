package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class SavingAccountTest {
    @Test
    public void shouldExistSavingAccountWhenCreated() {
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
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        //region given
        UUID stubId = null;
        Client stubClient = new Client(UUID.randomUUID(), "some name");
        double stubAmount = 1;

        SavingAccount sut = null;
        //endregion

        try {
            //region when
            sut = new SavingAccount(stubId, stubClient, stubAmount);
            //endregion

            //region then
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        } finally {
            assertNull(sut);
        }
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = null;
        double stubAmount = 1;

        SavingAccount sut = null;
        //endregion

        try {
            //region when
            sut = new SavingAccount(stubId, stubClient, stubAmount);
            //endregion

            //region then
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        } finally {
            assertNull(sut);
        }
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountLessThanZero() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "some name");
        double stubAmount = -1;

        SavingAccount sut = null;
        //endregion

        try {
            //region when
            sut = new SavingAccount(stubId, stubClient, stubAmount);
            //endregion

            //region then
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        } finally {
            assertNull(sut);
        }
        //endregion
    }
}
