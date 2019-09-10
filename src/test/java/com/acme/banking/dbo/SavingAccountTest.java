package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SavingAccountTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");

        SavingAccount sut = new SavingAccount(stubId, stubClient, 0);

        assertSame(stubId, sut.getClientId());
        assertSame(stubClient, sut.getClient());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingAndIdIsNull() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");

        SavingAccount sut = new SavingAccount(null, stubClient, 0);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingAndClientIsNull() {
        UUID stubId = UUID.randomUUID();

        SavingAccount sut = new SavingAccount(stubId, null, 0);
    }

    @Test
    public void shouldSaveAmountWhenCreated() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");

        SavingAccount sut = new SavingAccount(stubId, stubClient, 123);

        assertEquals(0, Double.compare(123, sut.getAmount()));
    }

    @Test
    public void shouldSaveClientWhenCreated() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");

        SavingAccount sut = new SavingAccount(stubId, stubClient, 123);

        assertSame(stubClient, sut.getClient());
    }

    @Test
    public void shouldSaveIdWhenCreated() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");

        SavingAccount sut = new SavingAccount(stubId, stubClient, 123);

        assertSame(stubId, sut.getId());
    }

    @Test
    public void shouldSaveClientIdWhenCreated() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");

        SavingAccount sut = new SavingAccount(stubId, stubClient, 123);

        assertSame(stubId, sut.getClientId());
    }
}
