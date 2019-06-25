package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SavingAccountTest {

    final String ANY_STRING = "ANY";
    final double ANY_DOUBLE = 1;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgExceptionWhenSavingAccountCreatedWithNullId() {
        //given
        Client stubClient = new Client(UUID.randomUUID(), ANY_STRING);
        //when
        new SavingAccount(null, stubClient, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgExceptionWhenSavingAccountCreatedWithNullClient() {
        new SavingAccount(UUID.randomUUID(), null, 0);
    }

    @Test
    public void shouldHeavingAmountEqualsInputAmount(){
        double expectedAmount = ANY_DOUBLE;
        SavingAccount sut = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), ""), expectedAmount);

        double actualAmount = sut.getAmount();

        assertTrue(actualAmount == expectedAmount);
    }


    @Test
    public void shouldHavingClientEqualsStubClientAndClientIsNotNull() {
        Client dummyClient = new Client(UUID.randomUUID(), ANY_STRING);
        SavingAccount sut = new SavingAccount(UUID.randomUUID(), dummyClient, ANY_DOUBLE);

        Client actualClient = sut.getClient();

        assertEquals( actualClient, dummyClient);
    }


    @Test
    public void shouldReturnCorrectUUIDAfterAccountWasCreated() {
        UUID expectedUUID = UUID.randomUUID();
        Client dummyClient = new Client(UUID.randomUUID(), ANY_STRING);
        SavingAccount sut = new SavingAccount(expectedUUID, dummyClient, ANY_DOUBLE);

        assertEquals( expectedUUID, sut.getId());
    }

}