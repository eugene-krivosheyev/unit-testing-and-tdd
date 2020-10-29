package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SavingAccountTest {

    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");


    @Test
    public void shouldSavingAccountCreatedWhenCorrectParams() {
        SavingAccount sut = new SavingAccount(ID_STUB, new Client(ID_STUB, "example"), 1);

        Assert.assertEquals(ID_STUB, sut.getId());
        Assert.assertEquals(1, sut.getAmount(), 0000);
        Assert.assertNotNull(sut.getClient());
    }

    @Test
    public void shouldNotCreateWhenIdIsNull() {
        try {
            SavingAccount sut = new SavingAccount(null, new Client(ID_STUB, "example"), 1);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("id is Null", e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateWhenClientIsNull() {
        try {
            SavingAccount sut = new SavingAccount(ID_STUB, null, 1);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("client is Null", e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateWhenAmountIsNotCorrect() {
        try {
            SavingAccount sut = new SavingAccount(ID_STUB, new Client(ID_STUB, "example"), -1);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("amount is not correct", e.getMessage());
        }
    }
}
