package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    private static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private static final Client CLIENT_STUB = new Client(ID_STUB, "dummy client name", Collections.emptyList());
    private static final double AMOUNT_STUB = 0.1;

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        SavingAccount sut = new SavingAccount(ID_STUB, CLIENT_STUB, AMOUNT_STUB);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", notNullValue()),
                        hasProperty("client", equalTo(CLIENT_STUB)),
                        hasProperty("amount", equalTo(AMOUNT_STUB))
                ));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        //region when
        SavingAccount sut = new SavingAccount(null, CLIENT_STUB, AMOUNT_STUB);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //region when
        SavingAccount sut = new SavingAccount(ID_STUB, null, AMOUNT_STUB);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenAmountIsNegative() {
        //region when
        SavingAccount sut = new SavingAccount(ID_STUB, CLIENT_STUB, -5.0);
        //endregion
    }
}
