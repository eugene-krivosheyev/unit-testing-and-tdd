package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class SavingAccountTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Test
    public void shouldStorePropertiesWhenCreated() throws IllegalAccessException {
        //region when
        Client client = new Client(ID_STUB, "dummy client name");
        SavingAccount sut = new SavingAccount(ID_STUB, client, 1000.00);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", notNullValue()),
                        hasProperty("amount", equalTo(1000.00))
                ));
        //endregion
    }

    @Test
    public void GetErrorNullIdWhenCreated() {
        final Client DUMMI_CLIENT = new Client(ID_STUB, "dummy client name");
        final double DIMMY_AMOUNT = 1000.00;

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(null, DUMMI_CLIENT, DIMMY_AMOUNT));
    }

    @Test
    public void GetErrorNullClientWhenCreated() {
        final double DIMMY_AMOUNT = 1000.00;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(ID_STUB, null, DIMMY_AMOUNT) );
    }

    @Test
    public void GetErrorLessThanMinValueAmountWhenCreated() {
        final Client DUMMI_CLIENT = new Client(ID_STUB, "dummy client name");
        final double DIMMY_AMOUNT = Double.MIN_VALUE - 1;
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  new SavingAccount(null, DUMMI_CLIENT, DIMMY_AMOUNT));
    }

    @Test
    public void GetErrorMoreThanMaxValueAmountWhenCreated() {
        final Client DUMMI_CLIENT = new Client(ID_STUB, "dummy client name");
        final double DIMMY_AMOUNT = Double.MAX_VALUE;
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  new SavingAccount(null, DUMMI_CLIENT, DIMMY_AMOUNT));
    }
}
