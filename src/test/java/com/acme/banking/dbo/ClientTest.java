package com.acme.banking.dbo;

import com.acme.banking.dbo.builder.ClientBuilder;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    Client sut;
    ClientBuilder builderSut = new ClientBuilder();
    public static final Collection<Account> ACCOUNT_STUB = new ArrayList<>();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        sut = builderSut.UUID(ID_STUB).name("dummy client name").accounts(ACCOUNT_STUB).build();
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", is("dummy client name"))
                ));
        //endregion
    }

    @Test
    public void shouldStorePropertiesWithNullClientWhenCreated() {
        //region when
        Account account = mock(Account.class);
        // ACCOUNT_STUB.add(account);

        sut = builderSut.UUID(ID_STUB).name("dummy client name").accounts(ACCOUNT_STUB).build();
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", is("dummy client name"))
                ));

        assertTrue(sut.getAccounts().isEmpty());

        //endregion

    }

    @Test
    public void shouldStorePropertiesWithNotNullClientWhenCreated() {
        //region when
        Account account = mock(Account.class);
        //Account account1 = mock(Account.class);
        ACCOUNT_STUB.add(account);

        sut = builderSut.UUID(ID_STUB).name("dummy client name").accounts(ACCOUNT_STUB).build();
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", is("dummy client name"))
                ));

        assertThat(sut.getAccounts().size(), is(1));
        assertThat(sut.getAccounts(), hasItem(account));
        //endregion

    }

    @Test
    public void AllGoodWhenCreated() {
        //region when
        Account account = mock(Account.class);
        when(account.getId()).
                thenReturn(ID_STUB);

        //Account account1 = mock(Account.class);
        ACCOUNT_STUB.add(account);

        sut = builderSut.UUID(ID_STUB).name("dummy client name").accounts(ACCOUNT_STUB).build();
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", is("dummy client name"))
                ));
        //assertThat(sut.getAccounts(), isNull());

        //assertThat(sut.getAccounts(), hasItem(account));
        assertThat(sut.getAccounts().size(), is(1));
        assertThat(sut.getAccounts(), hasItem(account));
        assertTrue(sut.getAccounts().iterator().next().getId().equals(account.getId()));

        //endregion

    }



    @Test
    public void shouldStorePropertiesWithTwoClientWhenCreated() {
        //region when
        Account account = mock(Account.class);
        Account account1 = mock(Account.class);
        ACCOUNT_STUB.add(account);
        ACCOUNT_STUB.add(account1);


        sut = builderSut.UUID(ID_STUB).name("dummy client name").accounts(ACCOUNT_STUB).build();
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", is("dummy client name"))
                ));
        //assertThat(sut.getAccounts(), isNull());

        //assertThat(sut.getAccounts(), hasItem(account));
        assertThat(sut.getAccounts().size(), is(2));
        assertThat(sut.getAccounts(), hasItems(account1,account));
        // assertThat(sut.getAccounts(), hasItems(account1,account2));

//        account.size = null
//client.account = account.client
        //endregion

    }

    @Test
    public void shouldGetExceptionWhenUUIDNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("id must be not null");

        sut = builderSut.UUID(null).name("dummy client name").accounts(ACCOUNT_STUB).build();
    }

    @Test
    public void shouldGetExceptionWhenNameNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name must be not null or empty");

        sut = builderSut.UUID(ID_STUB).name(null).accounts(ACCOUNT_STUB).build();
    }

    @Test
    public void shouldTGetExceptionWhenNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name must be not null or empty");

        sut = builderSut.UUID(ID_STUB).name("").accounts(ACCOUNT_STUB).build();
    }

    @Test
    public void shouldTGetExceptionWhenAccountsIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("accounts must be not null");

        sut = builderSut.UUID(ID_STUB).name("dummy client name").accounts(null).build();
    }
}
