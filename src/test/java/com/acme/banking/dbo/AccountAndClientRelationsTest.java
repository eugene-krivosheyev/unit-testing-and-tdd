package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.errors.UniqueConstraintException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class AccountAndClientRelationsTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private final UUID stubId = UUID.randomUUID();
    private final String stubName = "dummy client name";
    private final double stubAmount = .1;

    @Test
    public void shouldAddRelationsWhenClientAndAccountCreated() throws UniqueConstraintException {

        //region when
        final Client sutClient = new Client(stubId, stubName);
        SavingAccount sutAccount = new SavingAccount(stubId, sutClient, stubAmount);
        sutAccount.linkTo(sutClient);
        //endregion

        //region then
        assertSame(sutAccount.getClient(), sutClient);
        assertTrue(sutClient.existAccount(sutAccount));
        //endregion
    }

    @Test
    public void throwsUniqueConstraintExceptionIfAccountHasExist() throws UniqueConstraintException {

        exception.expect(UniqueConstraintException.class);
        exception.expectMessage("Account has exist");

        //region when
        final Client sutClient = new Client(stubId, stubName);
        SavingAccount sutAccount = new SavingAccount(stubId, sutClient, stubAmount);
        sutAccount.linkTo(sutClient);
        sutClient.addAccount(sutAccount);
        //endregion

        //region then
        assertSame(sutAccount.getClient(), sutClient);
        assertTrue(sutClient.existAccount(sutAccount));
        //endregion
    }
}
