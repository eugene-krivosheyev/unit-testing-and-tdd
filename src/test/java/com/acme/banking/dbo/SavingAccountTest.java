package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class SavingAccountTest {
    @Test
    public void shouldThrowExceptionWhenNullUUID(){
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        try {
            Account sut = new SavingAccount(null, dummyClient,0);
        } catch (IllegalArgumentException ex){
            assertTrue(ex.getMessage().equals("null id"));
        }
    }

    @Test
    public void shouldThrowExceptionWhenNullClient(){
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        try {
            Account sut = new SavingAccount(stubId, null,0);
        } catch (IllegalArgumentException ex){
            assertTrue(ex.getMessage().equals("null client"));
        }
    }
}
