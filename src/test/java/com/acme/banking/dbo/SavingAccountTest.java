package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    @Test
    public void savePropertiesWhenCreatedSavingAccount() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(5, "Dummy");
        double stubAmount = 1.5;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        not(0)
                ));

        assertThat(sut.getClient(),
                allOf(
                        is(stubClient),
                        notNullValue()
                ));
        assertThat(sut.getAmount(), equalTo(stubAmount));
        //endregion
    }

    @Test
    public void shouldGetAcountIdWhenCreated() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(5, "Dummy");
        double stubAmount = 1.5;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        Assert.assertEquals(stubId, sut.getId());
        //endregion
    }

    @Test
    public void shouldGetClientIdWhenCreated() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(5, "Dummy");
        double stubAmount = 1.5;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        Assert.assertEquals(stubClient.getId(), sut.getClientId().intValue());
        //endregion
    }

    @Test
    public void shouldGetAmountWhenCreated() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(5, "Dummy");
        double stubAmount = 1.5;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        Assert.assertEquals(stubAmount, sut.getAmount(), 0.00000001);
        //endregion
    }

    @Test
    public void shouldGetClientWhenCreated() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(5, "Dummy");
        double stubAmount = 1.5;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        Assert.assertEquals(stubClient, sut.getClient());
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCreatedWithInvalidId() {
        //region given
        int stubId = 0;
        Client stubClient = new Client(5, "Dummy");
        double stubAmount = 1.5;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion
    }
}
