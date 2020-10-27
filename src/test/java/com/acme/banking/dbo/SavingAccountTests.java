package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.UUID;

@SuppressWarnings("FieldCanBeLocal")
public class SavingAccountTests {
    private final UUID DUMMY_ID = UUID.randomUUID();
    private final Client DUMMY_CLIENT = new Client(DUMMY_ID, "DUMMY_NAME_CLIENT");
    private final double DUMMY_AMOUNT = 0.;
    private final double DOUBLE_DELTA = 0.00001;
    private final double NEGATIVE_AMOUNT = -0.;

    @Test
    public void shouldCreateSavingAccountWhenValidArguments() {
        SavingAccount sut = new SavingAccount(DUMMY_ID, DUMMY_CLIENT, DUMMY_AMOUNT);

        assertEquals(DUMMY_ID, sut.getId());
        assertEquals(DUMMY_CLIENT, sut.getClient());
        assertEquals(DUMMY_AMOUNT, sut.getAmount(), DOUBLE_DELTA);
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
        new SavingAccount(null, DUMMY_CLIENT, NEGATIVE_AMOUNT);
    }
}
