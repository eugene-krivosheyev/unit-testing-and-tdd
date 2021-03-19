package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    private final UUID ID_STUB = UUID.randomUUID();
    private final Client client = new Client(UUID.randomUUID(), "Dummy");

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        double amount = 10000;
        SavingAccount sut = new SavingAccount(ID_STUB, client, amount);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", notNullValue()),
                        hasProperty("client", instanceOf(Client.class)),
                        hasProperty("amount", equalTo(amount))
                ));
        //endregion
    }

    @Test
    public void amountShuntBeLessThenZero(){
        double amount = -10;
        try {
            SavingAccount sut = new SavingAccount(ID_STUB, client, amount);
        } catch (IllegalArgumentException e) {
            assertEquals("Amount can't be negative", e.getMessage());
        }
    }
}
