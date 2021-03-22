package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class SavingAccountTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Test
    public void shouldCreatedAccountWhenCreated () {
        Client fixture = new Client(ID_STUB, "dummy client name");
        SavingAccount sut = new SavingAccount(ID_STUB,fixture,0);

        Assert.assertTrue(sut.getId()!=null);
        Assert.assertTrue(sut.getClient()!=null);
        Assert.assertTrue(sut.getAmount()>=0);
    }
}
