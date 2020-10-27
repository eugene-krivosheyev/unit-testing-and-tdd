package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.UUID;

public class SavingAccountTests {
    private final static UUID DUMMY_ID = UUID.randomUUID();
    private final static Client DUMMY_CLIENT = new Client(DUMMY_ID, "DUMMY_ID");
    private final static double DUMMY_AMOUNT = 0;

    @Test
    public void shouldCreateSavingAccountWhenValidArguments() {
        SavingAccount sut = new SavingAccount(DUMMY_ID, DUMMY_CLIENT, DUMMY_AMOUNT);

        assertEquals(DUMMY_ID, sut.getId());
        assertEquals(DUMMY_CLIENT, sut.getClient());
        assertEquals(DUMMY_AMOUNT, sut.getAmount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateAccountWhenIdIsNull() {
        new SavingAccount(null, DUMMY_CLIENT, DUMMY_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateAccountWhenClientIsNull() {
        new SavingAccount(DUMMY_ID, null, DUMMY_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateAccountWhenAmountIsNegative() {
        new SavingAccount(null, DUMMY_CLIENT, -0.);
    }
}
