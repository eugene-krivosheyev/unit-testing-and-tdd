package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.*;

public class SavingAccountTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private UUID stubId;
    private Client stubClient;

    @Before
    public void setUp() {
        stubId = UUID.randomUUID();
        stubClient = new Client(stubId, "dummy client name");
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {
        SavingAccount sut = new SavingAccount(stubId, stubClient, 0);

        assertSame(stubId, sut.getClientId());
        assertSame(stubClient, sut.getClient());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingAndIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("id is null");

        SavingAccount sut = new SavingAccount(null, stubClient, 0);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingAndClientIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("client is null");

        SavingAccount sut = new SavingAccount(stubId, null, 0);
    }

    @Test
    public void shouldSaveAmountWhenCreated() {
        SavingAccount sut = new SavingAccount(stubId, stubClient, 123);

        assertEquals(0, Double.compare(123, sut.getAmount()));
    }

    @Test
    public void shouldSaveClientWhenCreated() {
        SavingAccount sut = new SavingAccount(stubId, stubClient, 123);

        assertSame(stubClient, sut.getClient());
    }

    @Test
    public void shouldSaveIdWhenCreated() {
        SavingAccount sut = new SavingAccount(stubId, stubClient, 123);

        assertSame(stubId, sut.getId());
    }

    @Test
    public void shouldSaveClientIdWhenCreated() {
        SavingAccount sut = new SavingAccount(stubId, stubClient, 123);

        assertSame(stubId, sut.getClientId());
    }
}
