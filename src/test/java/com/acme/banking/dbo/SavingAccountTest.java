package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class SavingAccountTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnClientWhenClientAdded() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client client = new Client(stubId, "dummy client name");
        SavingAccount account = new SavingAccount(stubId, client, 0);
        //endregion

        //region then
        assertTrue(account.getClient() == client);

        //endregion
    }

    @Test
    public void shouldClientIdsIsEqualWhenSaved() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client client = new Client(stubId, "dummy client name");
        SavingAccount account = new SavingAccount(stubId, client, 0);
        //endregion

        //region then
        assertTrue(account.getClientId() == client.getId());
        //endregion
    }

    @Test
    public void shouldCreatedClientHasIdWhenSaved() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client client = new Client(stubId, "dummy client name");
        SavingAccount account = new SavingAccount(stubId, client, 0);
        //endregion

        //region then
        assertTrue(account.getId() == stubId);
        //endregion
    }

    @Test
    public void shouldAmountsIsEqualWhenSaved() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client client = new Client(stubId, "dummy client name");
        Integer amount = 0;
        SavingAccount account = new SavingAccount(stubId, client, amount);
        //endregion

        //region then
        assertTrue(account.getAmount() == amount);
        //endregion
    }

    @Test
    public void shouldShowExceptionWhenIdIsNull() {

        thrown.expect(IllegalArgumentException.class);

        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client client = new Client(stubId, "dummy client name");
        Integer amount = 0;
        SavingAccount account = new SavingAccount(null, client, amount);
        //endregion
    }

    @Test
    public void shouldShowExceptionWhenClientIsNull() {

        thrown.expect(IllegalArgumentException.class);

        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client client = new Client(stubId, "dummy client name");
        Integer amount = 0;
        SavingAccount account = new SavingAccount(stubId, null, amount);
        //endregion
    }
}
