package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

public class SavingAccountTest {
    private final UUID ID_STUB = UUID.randomUUID();
    private final Client client = new Client(UUID.randomUUID(), "Dummy");

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        double amount = 10000;
        SavingAccount sut = new SavingAccount(ID_STUB, client, amount);

        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", instanceOf(Client.class)),
                        hasProperty("amount", equalTo(amount))
                ));
    }

    @Test
    public void amountShuntBeLessThenZero(){
        double amount = -10;
        expectedEx.expectMessage("Amount can't be negative");
        new SavingAccount(ID_STUB, client, amount);
    }
}
