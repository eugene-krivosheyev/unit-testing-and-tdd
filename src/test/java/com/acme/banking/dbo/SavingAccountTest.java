package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Client client = new Client(clientId, "dummy client name");
        double amount = 1000.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, client, amount);
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        notNullValue()
                ));
        assertThat(sut.getClient(),
                allOf(
                    equalTo(client),
                        notNullValue()
        ));
        assertThat(sut.getAmount(),
                allOf(
                        equalTo(amount)
                ));
        //endregion
    }

    @Test
    public void shouldReturnClientWhenClientGetted() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Client client = new Client(clientId, "dummy client name");
        SavingAccount sut = new SavingAccount(stubId, client, 1000.0);
        //endregion

        //region when
        Client clientGetted = sut.getClient();
        //endregion

        //region then
        assertThat(clientGetted,
                allOf(
                        equalTo(client),
                        notNullValue()
                ));
        //endregion
    }

    @Test
    public void shouldReturnAmountWhenAmmountGetted() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Client client = new Client(clientId, "dummy client name");
        double amount = 1000.0;
        SavingAccount sut = new SavingAccount(stubId, client, amount);
        //endregion

        //region when
        double amountGetted = sut.getAmount();
        //endregion

        //region then
        assertThat(amountGetted,
                allOf(
                        equalTo(amount)
                ));
        //endregion
    }

    @Test
    public void shouldReturnIdWhenIdGetted() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Client client = new Client(clientId, "dummy client name");
        double amount = 1000.0;
        SavingAccount sut = new SavingAccount(stubId, client, amount);
        //endregion

        //region when
        UUID idGetted = sut.getId();
        //endregion

        //region then
        assertThat(idGetted,
                allOf(
                        equalTo(stubId),
                        notNullValue()
                ));
        //endregion
    }

    @Test
    public void shouldReturnClientIdWhenClientIdGetted() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Client client = new Client(clientId, "dummy client name");
        double amount = 1000.0;
        SavingAccount sut = new SavingAccount(stubId, client, amount);
        //endregion

        //region when
        UUID clientIdGetted = sut.getClientId();
        //endregion

        //region then
        assertThat(clientIdGetted,
                allOf(
                        equalTo(clientId),
                        notNullValue()
                ));
        //endregion
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldReturnExeptionOnNullableParams() {
        new SavingAccount(null, null, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldReturnExeptionWhenNullableId() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client(clientId, "dummy client name");
        new SavingAccount(null, client, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldReturnExeptionWhenNullableClient() {
        UUID stubId = UUID.randomUUID();
        new SavingAccount(stubId, null, 0);
    }
}