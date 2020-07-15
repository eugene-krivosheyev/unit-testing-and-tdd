package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static java.lang.Math.abs;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {

    private SavingAccount sut;

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        Long stubId = (long) abs(Math.random());
        double stubAmount = Math.random();

        Long stubClientId = stubId + 1;
        String stubClientName = "dummy client name";
        Client stubClient = new Client(stubClientId, stubClientName);
        //endregion

        //region when
        sut = new SavingAccount(stubId,stubClient,stubAmount);
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        notNullValue()
                ));
        assertThat(sut.getClient(),
                allOf(
                        equalTo(stubClient),
                        notNullValue()
                ));
        assertThat(sut.getClientId(),
                allOf(
                        equalTo(stubClientId),
                        notNullValue()
                ));
        //endregion
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldErrorWhenIdIsNull() {
        //region given
        Long stubId = null;
        double stubAmount = Math.random();

        Long stubClientId = (long) abs(Math.random());
        String stubClientName = "dummy client name";
        Client stubClient = new Client(stubClientId, stubClientName);
        //endregion

        //region when
        sut = new SavingAccount(stubId,stubClient,stubAmount);
        //endregion
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldErrorWhenIdLessThenZero() {
        //region given
        Long stubId = (long) -1;
        double stubAmount = Math.random();

        Long stubClientId = stubId + 1;
        String stubClientName = "dummy client name";
        Client stubClient = new Client(stubClientId, stubClientName);
        //endregion

        //region when
        sut = new SavingAccount(stubId,stubClient,stubAmount);
        //endregion
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldErrorWhenAmountIsLessThenZero() {
        //region given
        Long stubId = (long) abs(Math.random());
        double stubAmount = -1.5;

        Long stubClientId = stubId + 1;
        String stubClientName = "dummy client name";
        Client stubClient = new Client(stubClientId, stubClientName);
        //endregion

        //region when
        sut = new SavingAccount(stubId,stubClient,stubAmount);
        //endregion
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldErrorWhenClientIsNull() {
        //region given
        Long stubId = (long) abs(Math.random());
        double stubAmount = Math.random();

        Client stubClient = null;
        //endregion

        //region when
        sut = new SavingAccount(stubId,stubClient,stubAmount);
        //endregion
    }


}
