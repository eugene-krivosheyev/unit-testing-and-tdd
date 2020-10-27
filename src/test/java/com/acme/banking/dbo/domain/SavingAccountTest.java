package com.acme.banking.dbo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

public class SavingAccountTest {

    private static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private static final String CLIENT_NAME = "dummy client name";
    private static final Client CLIENT = new Client(ID_STUB, CLIENT_NAME, Collections.emptyList());
    private static final double AMOUNT = 10;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
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

    @Test
    public void shouldNotCreateWhenIdNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("id can't be null");
        SavingAccount sut = new SavingAccount(null, CLIENT, AMOUNT);
    }

    @Test
    public void shouldNotCreateWhenClientNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("client can't be null");
        SavingAccount sut = new SavingAccount(ID_STUB, null, AMOUNT);
    }

    @Test
    public void shouldNotCreateWhenAmountNegative() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("amount can't be negative");
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
