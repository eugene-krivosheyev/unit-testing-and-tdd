package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {

    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Test
    public void shouldStorePropertiesWhenCreated() {

        //given
        Client client = new Client(ID_STUB, "test");
        SavingAccount sut = new SavingAccount(ID_STUB, client, -100000.00);

        //then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", notNullValue()),
                        hasProperty("client", equalTo(client)),
                        hasProperty("amount", equalTo(-100000.00))
                ));
    }
}
