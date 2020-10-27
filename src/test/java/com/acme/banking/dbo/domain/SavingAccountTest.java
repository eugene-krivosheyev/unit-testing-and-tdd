package com.acme.banking.dbo.domain;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

public class SavingAccountTest {

    private static final UUID ID_STUB = UUID.randomUUID();
    private static final String CLIENT_NAME = "dummy client name";
    private static final Client CLIENT = new Client(ID_STUB, CLIENT_NAME);
    private static final double AMOUNT = 10;

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        SavingAccount sut = new SavingAccount(ID_STUB, CLIENT, AMOUNT);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", is(CLIENT)),
                        hasProperty("client", notNullValue()),
                        hasProperty("amount", equalTo(AMOUNT))
                ));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdNull() {
        SavingAccount sut = new SavingAccount(null, CLIENT, AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenClientNull() {
        SavingAccount sut = new SavingAccount(ID_STUB, null, AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenAmountNegative() {
        SavingAccount sut = new SavingAccount(ID_STUB, CLIENT, -2);
    }

    @Test
    public void shouldGetId() {
        SavingAccount sut = new SavingAccount(ID_STUB, CLIENT, AMOUNT);
        assertEquals(ID_STUB, sut.getId());
    }

    @Test
    public void shouldGetClient() {
        SavingAccount sut = new SavingAccount(ID_STUB, CLIENT, AMOUNT);
        assertEquals(CLIENT, sut.getClient());
    }

    @Test
    public void shouldGetAmount() {
        SavingAccount sut = new SavingAccount(ID_STUB, CLIENT, AMOUNT);
        assertEquals(AMOUNT, sut.getAmount(), 0);
    }
}
