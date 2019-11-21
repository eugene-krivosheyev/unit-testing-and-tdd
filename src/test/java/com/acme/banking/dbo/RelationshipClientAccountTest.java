package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.RelationshipClientAccount;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;

public class RelationshipClientAccountTest {

    private Client stubClient;
    private Client dummyClient;
    private SavingAccount dummySavingAccountOne;
    private SavingAccount dummySavingAccountTwo;
    private List<SavingAccount> stubAccounts;

    @Before
    public void setUp() {
        stubClient = mock(Client.class);
        dummyClient = mock(Client.class);
        dummySavingAccountOne = mock(SavingAccount.class);
        dummySavingAccountTwo = mock(SavingAccount.class);
        stubAccounts = new ArrayList<>();
        stubAccounts.add(dummySavingAccountOne);
        stubAccounts.add(dummySavingAccountTwo);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test @Ignore
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
        setUp();

        RelationshipClientAccount sut = new RelationshipClientAccount(stubClient, stubAccounts);

        Assert.assertNotNull(sut);
        Assert.assertEquals(stubClient, sut.getClient());
        Assert.assertEquals(stubAccounts, sut.getSavingAccountList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenNoAccounts() {
        setUp();

        new RelationshipClientAccount(dummyClient, emptyList());
    }

    @Test
    public void shouldGetErrorWhenClientIsNull() {
        setUp();

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("client = null");

        new RelationshipClientAccount(null, stubAccounts);
    }

    @Test
    public void shouldGetErrorWhenAccountsListIsNull(){
        setUp();

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("savingAccountList = null or empty");

        new RelationshipClientAccount(dummyClient, null);
    }
}
