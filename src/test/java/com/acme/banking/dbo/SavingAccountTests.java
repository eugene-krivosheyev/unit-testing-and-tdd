package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.util.UUID;

@SuppressWarnings("FieldCanBeLocal")
public class SavingAccountTests {
    private final UUID DUMMY_ID = UUID.randomUUID();
    private final Client DUMMY_CLIENT = new Client(DUMMY_ID, "DUMMY_NAME_CLIENT");
    private final double DUMMY_AMOUNT = 0.;
    private final double DOUBLE_DELTA = 0.00001;
    private final double NEGATIVE_AMOUNT = -0.;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateSavingAccountWhenValidArguments() {
        SavingAccount sut = new SavingAccount(DUMMY_ID, DUMMY_CLIENT, DUMMY_AMOUNT);

        assertEquals(DUMMY_ID, sut.getId());
        assertEquals(DUMMY_CLIENT, sut.getClient());
        assertEquals(DUMMY_AMOUNT, sut.getAmount(), DOUBLE_DELTA);
    }

    @Test
    public void shouldNotCreateAccountWhenIdIsNull() {
        thrown.expect(IllegalArgumentException.class);

        new SavingAccount(null, DUMMY_CLIENT, DUMMY_AMOUNT);
    }

    @Test
    public void shouldNotCreateAccountWhenClientIsNull() {
        thrown.expect(IllegalArgumentException.class);

        new SavingAccount(DUMMY_ID, null, DUMMY_AMOUNT);
    }

    @Test
    public void shouldNotCreateAccountWhenAmountIsNegative() {
        thrown.expect(IllegalArgumentException.class);

        new SavingAccount(null, DUMMY_CLIENT, NEGATIVE_AMOUNT);
    }
}
