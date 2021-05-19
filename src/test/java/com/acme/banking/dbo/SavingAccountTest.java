package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    @Test
    public void shouldNotCreatedWhenIdIsNegative(){
        int negativeId = -1;
        Client dummyClient = new Client(1, "Client name");
        double dummyAmount = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(negativeId, dummyClient, dummyAmount),
                "Expected IllegalArgumentException to be thrown if Saving Account Id is less than 0.");
        assertEquals(thrown.getMessage(), "Saving Account Id is less than 0.");
    }

    @Test
    public void shouldStoreWhenCreated(){
        int validId = 1;
        Client validClient = new Client(1, "Client name");
        double validAmount = 0;

        SavingAccount sut = new SavingAccount(validId, validClient, validAmount);
        assertAll("",
                () -> assertEquals(validId, sut.getId()),
                () -> assertEquals(validClient, sut.getClient()),
                () -> assertEquals(validAmount, sut.getAmount()));
    }
}
