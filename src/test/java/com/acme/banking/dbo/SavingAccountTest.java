package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SavingAccountTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgExceptionWhenClientCreatedWithNullId() {
        //given
        String clientName = "Abba";
        Client client = new Client(UUID.randomUUID(), clientName);
        //when
        new SavingAccount(null, client, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgExceptionWhenClientCreatedWithNullClient() {
        //given
        String clientName = "Abba";
        Client client = new Client(UUID.randomUUID(), clientName);
        //when
        new SavingAccount(UUID.randomUUID(), null, 0);
    }

    @Test
    public void shouldHeavingAmountEquelInputAmount(){
        //given
        double stub = 12;
        SavingAccount sut = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), ""), stub);

        //when
        double amount = sut.getAmount();

        //then
        assertTrue(amount == stub);
    }


    @Test
    public void shouldHavingClientEqualsStubClient(){
        //given
        Client stubClient = new Client(UUID.randomUUID(), "");
        SavingAccount sut = new SavingAccount(UUID.randomUUID(), stubClient, 10);

        //when
        Client client = sut.getClient();

        //then
        assertEquals( stubClient, client);
    }

}