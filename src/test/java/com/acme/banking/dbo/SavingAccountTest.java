package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {
    private final int ID_STUB = 12;
    private final Client client = new Client(1, "Dummy");
    double amount = 10000;


    @Test
    public void shouldStorePropertiesWhenCreated() {
        SavingAccount sut = new SavingAccount(ID_STUB, client, amount);

        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", instanceOf(Client.class)),
                        hasProperty("amount", equalTo(amount))
                ));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0})
    void amountShuntBeLessThenZero(int amount){
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ID_STUB, client, amount));
    }

    @Test
    public void idShuntBeZero() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(0, client, amount));
    }

    @Test
    public void clientShuntBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ID_STUB, null, amount));
    }
}
