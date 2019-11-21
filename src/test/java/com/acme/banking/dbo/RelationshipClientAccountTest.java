package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.RelationshipClientAccount;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;

public class RelationshipClientAccountTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void shouldCreateRelationClientManyAccountWhenManyAccount() {
        final Client stubClient = mock(Client.class);
        final SavingAccount dummySavingAccountOne = mock(SavingAccount.class);
        final SavingAccount dummySavingAccountTwo = mock(SavingAccount.class);
        final List<SavingAccount> stubAccounts = new ArrayList<>();
        stubAccounts.add(dummySavingAccountOne);
        stubAccounts.add(dummySavingAccountTwo);

        RelationshipClientAccount sut = new RelationshipClientAccount(stubClient, stubAccounts);

        Assert.assertNotNull(sut);
        Assert.assertEquals(stubClient, sut.getClient());
        Assert.assertEquals(stubAccounts, sut.getSavingAccountList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenNoAccounts() {
        Client dummyClient = mock(Client.class);

        new RelationshipClientAccount(dummyClient, emptyList());
    }

    @Test
    public void shouldGetErrorWhenClientIsNull() {
        final SavingAccount dummySavingAccount = mock(SavingAccount.class);
        final List<SavingAccount> stubAccounts = new ArrayList<>();
        stubAccounts.add(dummySavingAccount);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("client = null");
        new RelationshipClientAccount(null, stubAccounts);
    }

    @Test
    public void shouldGetErrorWhenAccountsListIsNull(){
        Client dummyClient = mock(Client.class);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("savingAccountList = null or empty");
        new RelationshipClientAccount(dummyClient, null);
    }
}