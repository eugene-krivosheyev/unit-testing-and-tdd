package com.acme.banking.dbo;

import com.acme.banking.dbo.builder.TestEntities;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.error.UniqueConstraintException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SavingAccountTest {

    @Test
    public void shouldAddAccountWhenClientAndAccountCreated() throws UniqueConstraintException {
        //region given
        TestEntities builder = new TestEntities.Builder().build();
        Client sutClient = builder.getClient();
        Account sutAccount = builder.getAccount();
        //endregion

        //region when
        sutClient.addAccount(sutAccount);
        //endregion

        //region then
        assertSame(sutAccount.getId(), builder.getId());
        assertSame(sutAccount.getClient(), sutClient);
        assertSame(sutAccount.getClientId(), builder.getId());
        assertEquals(sutAccount.getAmount(), builder.getAmount(), 0.0);
        assertTrue(sutClient.existAccount(sutAccount));
        //endregion
    }

    @Test
    public void shouldRemoveAccountWhenClientAndAccountCreated() {
        //region given
        TestEntities builder = new TestEntities.Builder().build();
        Client sutClient = builder.getClient();
        Account sutAccount = builder.getAccount();
        //endregion

        //region when
        sutClient.removeAccount(sutAccount);
        //endregion

        //region then
        assertNull(sutAccount.getClient());
        assertTrue(sutClient.hasAccounts());
        //endregion
    }

    @Test
    public void shouldRemoveClientWhenAccountCreated() throws UniqueConstraintException {
        //region given
        TestEntities builder = new TestEntities.Builder().build();
        Client sutClient = builder.getClient();
        Account sutAccount = builder.getAccount();
        //endregion

        //region when
        sutClient.addAccount(sutAccount);
        sutAccount.removeClient();
        //endregion

        //region then
        assertNull(sutAccount.getClient());
        //endregion
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        new SavingAccount(null, 0.3);
    }
}
