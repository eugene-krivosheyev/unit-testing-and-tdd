package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    @Test
    public void shouldThrowWhenIdIsNegative(){
        //given
        int accountId = -1;
        Client clientUnderTest = new Client(1, "Client Name #1");
        double amountValue = 100;

        //when
        //then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(accountId, clientUnderTest, amountValue),
                "Expected IllegalArgumentException to be thrown if Saving Account Id is less than 0.");
        assertEquals(thrown.getMessage(), "Saving Account Id is less than 0.");
    }

    @Test
    public void shouldSetIdWhenValid(){
        int accountId = 1;
        Client clientUnderTest = new Client(1, "Client Name #1");
        double amountValue = 1;

        SavingAccount sut = new SavingAccount(accountId, clientUnderTest, amountValue);

        assertEquals(sut.getId(), accountId);
    }
}
