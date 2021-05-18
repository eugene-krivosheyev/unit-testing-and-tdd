package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Saving Account Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SavingAccountTest {

    Client client;

    @BeforeAll
    public void init() {
        client = new Client(1, "Test Client");
    }

    @Test
    public void shouldNotCreateSavingAccountWhenIdIsNegative() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, client, 10));
        assertEquals(thrown.getMessage(), "id < 0");
    }

    @Test
    public void shouldNotCreateSavingAccountWhenAmmountIsNegative() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, client, -10));
        assertEquals(thrown.getMessage(), "amount < 0");
    }

    @Test
    public void shouldNotCreateSavingAccountWhenClientIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, null, 10));
        assertEquals(thrown.getMessage(), "client is null!");
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        SavingAccount savingAccount = new SavingAccount(1, client, 1);
        assertAll("SavingAccount store its properties",
                () -> assertEquals(1, savingAccount.getId()),
                () -> assertEquals(1, savingAccount.getAmount()),
                () -> assertEquals(client, savingAccount.getClient())
        );
    }
}
