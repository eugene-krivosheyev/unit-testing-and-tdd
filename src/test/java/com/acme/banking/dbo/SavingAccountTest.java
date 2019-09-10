package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SavingAccountTest {

    private final UUID stubId = UUID.randomUUID();
    private final String stubName = "dummy client name";
    private final double stubAmount = .1;

    @Test
    public void shouldAddAccountWhenClientAndAccountCreated() {
        //region given
        Client sutClient = new Client(stubId, stubName);
        SavingAccount sutAccount = new SavingAccount(stubId, sutClient, stubAmount);
        //endregion

        //region when
        sutClient.addAccount(sutAccount);
        //endregion

        //region then
        assertSame(sutAccount.getId(), stubId);
        assertSame(sutAccount.getClient(), sutClient);
        assertSame(sutAccount.getClientId(), stubId);
        assertEquals(sutAccount.getAmount(), stubAmount, 0.0);
        assertTrue(sutClient.containsAccount(sutAccount));
        //endregion
    }

    @Test
    public void shouldRemoveAccountWhenClientAndAccountCreated() {
        //region given
        Client sutClient = new Client(stubId, stubName);
        SavingAccount sutAccount = new SavingAccount(stubId, sutClient, stubAmount);
        //endregion

        //region when
        sutClient.addAccount(sutAccount);
        sutClient.removeAccount(sutAccount);
        //endregion

        //region then
        assertTrue(sutClient.isAccountsEmpty());
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdNull() {
        new SavingAccount(stubId, null, stubAmount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenClientNull() {
        Client sutClient = new Client(stubId, stubName);
        new SavingAccount(null, sutClient, stubAmount);
    }
}
