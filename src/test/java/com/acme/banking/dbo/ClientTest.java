package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ClientTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Rule
    public final TestRule globalTimeout = Timeout.millis(30);

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        //region then
        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));
        //endregion
    }

    @Test
    public void shouldAddAccountToAccountsList(){
        //region given
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        Account account = new SavingAccount(stubId,dummyClient,0d);
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        Assume.assumeTrue(sut.getAccounts().isEmpty());

        sut.addAccount(account);

        //region then
        assertTrue(sut.getAccounts().size() == 1);
        assertTrue(sut==account.getClient());
        //endregion
    }

    @Test
    public void shouldRemoveAccountFromAccountList(){
        //region given
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        Account account = new SavingAccount(stubId,dummyClient,0d);
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        sut.addAccount(account);

        //region then
        assertTrue(sut.getAccounts().size() == 1);
        //endregion

        sut.removeAccount(account);

        //region then
        assertTrue(sut.getAccounts().size() == 0);
        //endregion
    }

    @Test
    public void shouldThrowExceptionWhenNullUUID(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");
        new Client(null, "dummy client name");
    }

    @Test
    public void shouldThrowExceptionWhenNullName(){
        UUID stubId = UUID.randomUUID();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null or empty");
        new Client(stubId, null);
    }

    @Test
    public void shouldThrowExceptionWhenEmptyName(){
        UUID stubId = UUID.randomUUID();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null or empty");
        new Client(stubId, "");
    }
}
