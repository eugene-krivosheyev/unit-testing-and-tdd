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
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldContainSameAccountInClientWhenCreated(){
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");

        SavingAccount acc = new SavingAccount(stubId, dummyClient,0);

        assertTrue(dummyClient.getAccounts().contains(acc));

    }

    @Test
    public void shouldThrowExceptionWhenNullUUID(){
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("null id");

        new SavingAccount(null, dummyClient,0);

    }

    @Test
    public void shouldThrowExceptionWhenNullClient(){
        UUID stubId = UUID.randomUUID();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("null client");
        new SavingAccount(stubId, null,0);
    }

    @Test
    public void shouldThrowExceptionWhenNullClientAndNullUUID(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("null id");
        new SavingAccount(null, null,0);
    }

    @Test
    public void shouldReturnSameClientId(){
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");

        SavingAccount acc = new SavingAccount(stubId, dummyClient,0);

        assertTrue(acc.getClientId().equals(stubId));
    }

    @Test
    public void shouldReturnSameClient(){
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");

        SavingAccount acc = new SavingAccount(stubId, dummyClient,0);

        assertTrue(acc.getClient().equals(dummyClient));
    }
}
