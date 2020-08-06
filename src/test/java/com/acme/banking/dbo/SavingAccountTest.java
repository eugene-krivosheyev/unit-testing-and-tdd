package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void savePropertiesWhenCreatedSavingAccount() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(1, "Dummy");
        double stubAmount = 1.0;
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
    public void shouldGetAccountIdWhenCreated() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(1, "Dummy");
        double stubAmount = 1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertThat("Get invalid id when account created", stubId, equalTo(sut.getId()));
        //endregion
    }

    @Test
    public void shouldGetClientIdWhenCreated() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(1, "Dummy");
        double stubAmount = 1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertThat("Get invalid client id when account created", stubClient.getId(), equalTo(sut.getClientId()));
        //endregion
    }

    @Test
    public void shouldGetAmountWhenCreated() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(1, "Dummy");
        double stubAmount = 1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertThat("Get invalid amount when account created", stubAmount, equalTo(sut.getAmount()));
        //endregion
    }

    @Test
    public void shouldGetClientWhenCreated() {
        //region given
        int stubId = 1;
        Client stubClient = new Client(1, "Dummy");
        double stubAmount = 1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion

        //region then
        assertThat("Get invalid client when account created", stubClient, equalTo(sut.getClient()));
        //endregion
    }

    @Test
    public void shouldNotCreateWhenIdEqualsZero() {
        exception.expect(IllegalArgumentException.class);
        //region given
        int stubId = 0;
        Client stubClient = new Client(1, "Dummy");
        double stubAmount = 1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion
    }

    @Test
    public void shouldNotCreateWhenIdLessZero() {
        exception.expect(IllegalArgumentException.class);
        //region given
        int stubId = -1;
        Client stubClient = new Client(1, "Dummy");
        double stubAmount = 1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion
    }

    @Test
    public void shouldNotCreateWhenClientIsNull() {
        exception.expect(IllegalArgumentException.class);
        //region given
        int stubId = 1;
        Client stubClient = null;
        double stubAmount = 1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion
    }

    @Test
    public void shouldNotCreateWhenAmountLessZero() {
        exception.expect(IllegalArgumentException.class);
        //region given
        int stubId = 1;
        Client stubClient = new Client(1, "Dummy");

        double stubAmount = -1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        //endregion
    }
}
