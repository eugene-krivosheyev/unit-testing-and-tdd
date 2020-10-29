package com.acme.banking.dbo.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {

    private static UUID ID_STUB;
    private static Client CLIENT;
    private static double AMOUNT;
    private static double NEGATIVE_AMOUNT;

    @Before
    public void init() {
        ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
        CLIENT = new ClientBuilder().build();
        AMOUNT = 10;
        NEGATIVE_AMOUNT = -2;
    }

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
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(null, CLIENT, AMOUNT));
        assertEquals("id can't be null", ex.getMessage());
    }

    @Test
    public void shouldNotCreateWhenClientNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ID_STUB, null, AMOUNT));
        assertEquals("client can't be null", ex.getMessage());
    }

    @Test
    public void shouldNotCreateWhenAmountNegative() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ID_STUB, CLIENT, NEGATIVE_AMOUNT));
        assertEquals("amount can't be negative", ex.getMessage());
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
