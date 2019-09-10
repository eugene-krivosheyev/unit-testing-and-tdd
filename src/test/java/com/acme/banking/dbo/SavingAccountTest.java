package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.errors.UniqueConstraintException;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SavingAccountTest {

    private final UUID stubId = UUID.randomUUID();
    private final String stubName = "dummy client name";
    private final double stubAmount = .1;

    @Test
    public void shouldAddAccountWhenClientAndAccountCreated() throws UniqueConstraintException {
        //region given
        Client sutClient = new Client(stubId, stubName);
        SavingAccount sutAccount = new SavingAccount(stubId, sutClient, stubAmount);
        //endregion

        //region then
        assertSame(sutAccount.getId(), stubId);
        assertSame(sutAccount.getClient(), sutClient);
        assertSame(sutAccount.getClientId(), stubId);
        assertEquals(sutAccount.getAmount(), stubAmount, 0.0);
        assertTrue(sutClient.existAccount(sutAccount));
        //endregion
    }

    @Test
    public void shouldRemoveAccountWhenClientAndAccountCreated() throws UniqueConstraintException {
        //region given
        Client sutClient = new Client(stubId, stubName);
        SavingAccount sutAccount = new SavingAccount(stubId, sutClient, stubAmount);
        //endregion

        //region when
        sutClient.removeAccount(sutAccount);
        //endregion

        //region then
        assertTrue(sutClient.hasAccounts());
        //endregion
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenIdIsNull() throws UniqueConstraintException {
        new SavingAccount(stubId, null, stubAmount);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenClientIsNull() throws UniqueConstraintException {
        Client sutClient = new Client(stubId, stubName);
        new SavingAccount(null, sutClient, stubAmount);
    }
}
