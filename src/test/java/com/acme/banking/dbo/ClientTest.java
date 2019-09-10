package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        //region then
        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));
        //endregion
    }

    @Test
    public void shouldElementContainedWhenElementAdded() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        Client sut = new Client(stubId, "dummy client name");
        SavingAccount account = new SavingAccount(accountId, sut, 1000.0);
        //endregion

        //region when
        sut.addAccount(account);
        //endregion

        //region then
        Collection<Account> accountList = sut.getAccountIds();
        Assert.assertTrue(accountList.contains(account));
        //endregion
    }

    @Test
    public void shouldElementNotContainedWhenElementRemoved() {
        //region given
        UUID stubId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        Client sut = new Client(stubId, "dummy client name");
        SavingAccount account = new SavingAccount(accountId, sut, 1000.0);
        sut.addAccount(account);
        //endregion

        //region when
        sut.removeAccount(account);
        //endregion

        //region then
        Collection<Account> accountList = sut.getAccountIds();
        Assert.assertFalse(accountList.contains(account));
        //endregion
    }

    @Test
    public void shouldReturnIdWhenIdGetted() {
        //region given
        UUID stubId = UUID.randomUUID();
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        //region when
        UUID id = sut.getId();
        //endregion

        //region then
        Assert.assertEquals(id, stubId);
        //endregion
    }

    @Test
    public void shouldReturnNameWhenNameGetted() {
        //region given
        UUID stubId = UUID.randomUUID();
        String sutName = "dummy client name";
        Client sut = new Client(stubId, sutName);
        //endregion

        //region when
        String name = sut.getName();
        //endregion

        //region then
        Assert.assertEquals(name, sutName);
        //endregion
    }

    @Test
    public void shouldReturnAccountsWhenAccountsGetted() {
        //region given
        UUID stubId = UUID.randomUUID();
        String sutName = "dummy client name";
        Client sut = new Client(stubId, sutName);
        //endregion

        //region when
        Collection<Account> accounts = sut.getAccountIds();
        //endregion

        //region then
        Assert.assertNotNull(accounts);
        //endregion
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldReturnExeptionWhenNullableId() {
        new Client(null, "name");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldReturnExeptionWhenNullableName() {
        UUID stubId = UUID.randomUUID();
        new Client(stubId, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldReturnExeptionWhenNullableParams() {
        new Client(null, null);
    }
}