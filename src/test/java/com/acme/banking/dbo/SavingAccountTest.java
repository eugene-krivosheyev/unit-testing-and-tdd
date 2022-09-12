package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Saving account test suite")
public class SavingAccountTest {

    private final static int clientId = 1;
    private final static String clientName = "dummy client name";

    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int savingAccountId = 1;
        final double amount = 5;

        Client sut = new Client(clientId, clientName);
        SavingAccount savingAccount = new SavingAccount(savingAccountId, sut, amount);

        assertAll("Saving Accounts store its properties",
                () -> assertEquals(savingAccountId, savingAccount.getId()),
                () -> assertEquals(sut, savingAccount.getClient()),
                () -> assertEquals(amount, savingAccount.getAmount())
        );
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithIncorrectId() {
        final int savingAccountId = -1;
        final double amount = 1;

        Client sut = new Client(clientId, clientName);

        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(savingAccountId, sut, amount));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithIncorrectClient() {
        final int savingAccountId = -1;
        final double amount = 1;

        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(savingAccountId, null, amount));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithIncorrectAmount() {

        final int savingAccountId = 2;
        final double amount = -1;

        Client sut = new Client(clientId, clientName);

        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(savingAccountId, sut, amount));
    }
}