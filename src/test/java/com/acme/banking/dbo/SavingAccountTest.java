package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Rule
    public final TestRule globalTimeout = Timeout.millis(20);

    @Test
    public void shouldSavePropertiesWhenCreated() {
        UUID stubID = UUID.randomUUID();
        Client stubClient = new Client(stubID, "dummy name");
        SavingAccount sut = new SavingAccount(stubID, stubClient, 100d);

        assertEquals(sut.getAmount(), 100d, 0.001d);
        assertThat(sut.getId(), CoreMatchers.allOf(equalTo(stubID), notNullValue()));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfClientIsNull() {
        thrown.expect(IllegalArgumentException.class);
        Client stubClient = null;
        UUID stubID = UUID.randomUUID();
        new SavingAccount(stubID, stubClient, 100d);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("id cannot be null");
        UUID stubID = null;
        Client stubClient = new Client(stubID, "dummy name");
        new SavingAccount(stubID, stubClient, 100d);
    }

    @Test
    public void shouldGetClientIdWhenAccountCreated() {
        UUID sutClientId = UUID.randomUUID();
        Client stubClient = new Client(sutClientId, "account owner name");
        SavingAccount stubAccount = new SavingAccount(sutClientId, stubClient, 100d);

        assertEquals(sutClientId, stubAccount.getClientId());
    }
}
