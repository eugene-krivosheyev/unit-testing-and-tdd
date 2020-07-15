package com.acme.banking.dbo.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        final long id = 2L;
        final Client client = new Client(1L, "name");
        final double amount = 2.0;

        SavingAccount sut = new SavingAccount(id, client, amount);

        assertThat(sut.getId(), equalTo(id));
        assertThat(sut.getAmount(), equalTo(amount));
        assertThat(sut.getClient(), equalTo(client));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenIdIsNull() {
        new SavingAccount(null, new Client(1L, "name"), 2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenIdIsNegative() {
        new SavingAccount(-1L, new Client(1L, "name"), 2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenClientIsNull() {
        new SavingAccount(1L, null, 2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenAmountIsNull() {
        new SavingAccount(1L, new Client(1L, "name"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenAmountIsNotPositive() {
        new SavingAccount(1L, new Client(1L, "name"), 0.0);
    }
}