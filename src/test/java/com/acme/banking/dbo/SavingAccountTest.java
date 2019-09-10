package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertSame;

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
    public void ShouldThrowExceptionWhenCreatedAndIdIsNull() {
        Client stubClient = new Client(null, "dummy client name");

        SavingAccount sut = new SavingAccount(null, stubClient, 0);
    }

    @Test(expected = NullPointerException.class)
    public void ShouldThrowExceptionWhenCreatedAndClientIsNull() {
        UUID stubId = UUID.randomUUID();

        SavingAccount sut = new SavingAccount(stubId, null, 0);
    }
}
