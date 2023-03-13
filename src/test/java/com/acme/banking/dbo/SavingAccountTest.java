package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    @Test
    public void shouldCreateSavingAccountWhenCorrectParameters(){
        Client dummyClient = new Client(1, "name");
        SavingAccount savingAccount = new SavingAccount(0, dummyClient, 0d);
        assertAll(
                ()-> assertEquals(0, savingAccount.getId()),
                ()-> assertEquals(dummyClient, savingAccount.getClient()),
                ()-> assertEquals(0d, savingAccount.getAmount())
        );
    }

    @Test
    public void shouldThrowIllegalExceptionWhenNegativeId() {
        Client dummyClient = new Client(1, "name");
        assertThrows(IllegalArgumentException.class,() -> new SavingAccount(-1, dummyClient, 3.0d));
    }

    @Test
    public void shouldThrowIllegalExceptionWhenNegativeAmount() {
        Client dummyClient = new Client(1, "name");
        assertThrows(IllegalArgumentException.class,() -> new SavingAccount(2, dummyClient, -0.01d));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenClientIsNull() {
        assertThrows(NullPointerException.class,() -> new SavingAccount(2, null, -0.01d));
    }
}
