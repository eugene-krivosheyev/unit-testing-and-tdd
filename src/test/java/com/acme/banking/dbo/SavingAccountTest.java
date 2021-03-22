package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {
    public static final int ID_STUB = 2;
    public static final String CLIENT_NAME = "dummy client name";
    public static final int CLIENT_ID = 3;
    public static final Client test_client = new Client(CLIENT_ID, CLIENT_NAME);
    public static final double TEST_AMOUNT = 123.4;

    @Test
    public void shouldStorePropertiesWhenCreated() {

        SavingAccount sut = new SavingAccount(ID_STUB, test_client, TEST_AMOUNT);

        assertAll("Client store its properties",
                () -> assertEquals(ID_STUB, sut.getId()),
                () -> assertEquals(test_client, sut.getClient()),
                () -> assertEquals(TEST_AMOUNT, sut.getAmount())
        );
    }

    @Test
    public void shouldReturnId() {
        SavingAccount account = new SavingAccount(ID_STUB, test_client, TEST_AMOUNT);
        int sut = account.getId();

        assertEquals(ID_STUB, sut);
    }

    @Test
    public void shouldReturnClient() {
        SavingAccount account = new SavingAccount(ID_STUB, test_client, TEST_AMOUNT);
        Client sut = account.getClient();

        assertEquals(test_client, sut);
    }

    @Test
    public void shouldReturnAmount() {
        SavingAccount account = new SavingAccount(ID_STUB, test_client, TEST_AMOUNT);
        double sut = account.getAmount();

        assertEquals(TEST_AMOUNT, sut, 0.001);
    }
}
