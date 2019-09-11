package com.acme.banking.dbo;

import com.acme.banking.dbo.builder.TestEntities;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.error.UniqueConstraintException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class AccountAndClientRelationsTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldAddRelationsWhenClientAndAccountCreated() throws UniqueConstraintException {
        //region given
        TestEntities builder = new TestEntities.Builder().build();
        Client sutClient = builder.getClient();
        Account sutAccount = builder.getAccount();
        //endregion

        //region when
        sutClient.addAccount(sutAccount);
        //endregion

        //region then
        assertSame(sutAccount.getClient(), sutClient);
        assertTrue(sutClient.existAccount(sutAccount));
        //endregion
    }

    @Test
    public void throwsUniqueConstraintExceptionIfAccountHasExist() throws UniqueConstraintException {
        //region when
        TestEntities builder = new TestEntities.Builder().build();
        Client sutClient = builder.getClient();
        Account sutAccount = builder.getAccount();
        //endregion

        exception.expect(UniqueConstraintException.class);
        exception.expectMessage("Account has exist");

        //region when
        sutClient.addAccount(sutAccount);
        sutClient.addAccount(sutAccount);
        //endregion

        //region then
        assertSame(sutAccount.getClient(), sutClient);
        assertTrue(sutClient.existAccount(sutAccount));
        //endregion
    }
}
