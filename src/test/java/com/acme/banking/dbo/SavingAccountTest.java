package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    private long stubId = 1;
    private final String stubName = "some stub name";
    private final Client stubClient = new Client(stubId, stubName);
    private double stubAmount = 100.0;

    @Test
    public void shouldSavePropertiesWhenCreated() {
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);

        assertThat(sut.getId(),
                allOf(
                        notNullValue(),
                        equalTo(stubId)
                ));

        assertThat(sut.getClient(),
                allOf(
                        notNullValue(),
                        equalTo(stubClient)
                ));

        assertThat(sut.getAmount(),
                allOf(
                        notNullValue(),
                        equalTo(stubAmount)
                ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenIdIsLessThanZero() {
        stubId = -1;

        new SavingAccount(stubId, stubClient, stubAmount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenClientIsNull() {
        new SavingAccount(stubId, null, stubAmount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenAmountIsLessThanZero() {
        stubAmount = -1.0;
        new SavingAccount(stubId, stubClient, stubAmount);
    }
}