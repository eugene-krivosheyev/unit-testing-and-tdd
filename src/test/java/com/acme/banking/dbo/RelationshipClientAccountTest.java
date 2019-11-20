package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.RelationshipClientAccount;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RelationshipClientAccountTest {

    @Test
    public void shouldCresteReletionClientManyAccountWhenManeAccountNotNull() {
        Client dummyClient = new Client(UUID.randomUUID(), "Dummy");
        SavingAccount dummySavingAccountOne = new SavingAccount(UUID.randomUUID(), 0.);
        SavingAccount dummySavingAccountTwo = new SavingAccount(UUID.randomUUID(), 0.);
        List<SavingAccount> dummySavingAccountList = new ArrayList<>();
        dummySavingAccountList.add(dummySavingAccountOne);
        dummySavingAccountList.add(dummySavingAccountTwo);

        RelationshipClientAccount sut = new RelationshipClientAccount(dummyClient, dummySavingAccountList);

        Assert.assertNotNull(sut);
        Assert.assertEquals(dummyClient, sut.getClient());
        Assert.assertEquals(dummySavingAccountList, sut.getSavingAccountList());
    }
}
