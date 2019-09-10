package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

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

        assertThat(sut.getName(),
                allOf(
                        equalTo("dummy client name"),
                        notNullValue()
                ));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateClientWhenNameIsEmpty() {
        String stubName = "";
        UUID stubId = UUID.randomUUID();

        new Client(stubId, stubName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateClientWhenIdIsNull() {
        String stubName = null;
        UUID stubId = UUID.randomUUID();

        new Client(stubId, stubName);
    }

    @Test
    public void shouldIncrementListOfAccountsAndClientGetAddedAccountWhenAddNewAccountToClient() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy name");
        Account stubAccount = new SavingAccount(stubId, stubClient, 100d);

        Client sut = new Client(stubId, "sut client name");
        sut.addAccount(stubAccount);

        assertEquals(1, sut.getAccounts().size());
        assertTrue(sut.getAccounts().contains(stubAccount));
    }

    @Test
    public void shouldDecrementListOfAccountAndClientLoseRemovedAccountWhenAccountRemoved() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy name");
        Account stubAccount = new SavingAccount(stubId, stubClient, 100d);
        Client sut = new Client(stubId, "sut client name");

        sut.addAccount(stubAccount);
        sut.removeAccount(stubAccount);

        assertEquals(0, sut.getAccounts().size());
        assertFalse(sut.getAccounts().contains(stubAccount));
    }

    @Test
    public void shouldBeSameAccountOwnerNameWhenAddingAccount() {
        UUID stubId = UUID.randomUUID();
        Client sut = new Client(stubId, "account owner name");
        SavingAccount account = new SavingAccount(stubId, new Client(UUID.randomUUID(), "some new person"), 100);
        sut.addAccount(account);

        assertEquals(sut.getAccounts().iterator().next().getClient().getName(), sut.getName());
        assertEquals(sut.getAccounts().iterator().next().getClient().getId(), sut.getId());
    }
}
