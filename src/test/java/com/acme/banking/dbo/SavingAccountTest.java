package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SavingAccountTest {
    private static final double AMOUNT_NEGATIVE_VALUE = 0 - Double.MIN_VALUE;
    private static Client CLIENT_MOCK = mock(Client.class);
    private static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private static final double AMOUNT_STUB = 0.1;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        SavingAccount sut = new SavingAccount(ID_STUB, CLIENT_MOCK, AMOUNT_STUB);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", notNullValue()),
                        hasProperty("client", equalTo(CLIENT_MOCK)),
                        hasProperty("amount", equalTo(AMOUNT_STUB))
                ));
        //endregion
    }

    @Test
    public void shouldNotCreateInstanceWhenIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("id is null"));
        new SavingAccount(null, CLIENT_MOCK, AMOUNT_STUB);
    }

    @Test
    public void shouldNotCreateInstanceWhenClientIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("client is null"));
        new SavingAccount(ID_STUB, null, AMOUNT_STUB);
    }

    @Test
    public void shouldNotCreateInstanceWhenAmountIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("amount is negative"));
        new SavingAccount(ID_STUB, CLIENT_MOCK, AMOUNT_NEGATIVE_VALUE);
    }
}
