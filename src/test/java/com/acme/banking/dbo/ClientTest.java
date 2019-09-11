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
        UUID stubId = UUID.randomUUID();

        Client sut = new Client(stubId, "dummy client name");

        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));
    }

    @Test
    public void shouldAddAccountToAccountsList(){
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        Account account = new SavingAccount(stubId,dummyClient,0d);

        Client sut = new Client(stubId, "dummy client name");

        Assume.assumeTrue(sut.getAccounts().isEmpty());

        sut.addAccount(account);

        assertTrue(sut.getAccounts().size() == 1);
        assertTrue(sut==account.getClient());
    }

    @Test
    public void shouldRemoveAccountFromAccountList(){
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        Account account = new SavingAccount(stubId,dummyClient,0d);

        Client sut = new Client(stubId, "dummy client name");

        sut.addAccount(account);

        assertTrue(sut.getAccounts().size() == 1);

        sut.removeAccount(account);

        assertTrue(sut.getAccounts().size() == 0);
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
