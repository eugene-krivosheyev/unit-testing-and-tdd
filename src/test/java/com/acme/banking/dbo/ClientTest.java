package com.acme.banking.dbo;

import com.acme.banking.dbo.builder.ClientBuilder;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
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

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    public static final Collection<Account> ACCOUNT_STUB = new ArrayList<>();
    Client sut;
    ClientBuilder builderSut;

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

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetExceptionWhenUUIDNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("id must be not null");

        sut = builderSut.UUID(null).name("dummy client name").accounts(ACCOUNT_STUB).build();
    }

    @Test
    public void shouldGetExceptionWhenNameNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name must be not null or empty");

        sut = builderSut.UUID(null).name(null).accounts(ACCOUNT_STUB).build();
    }

    @Test
    public void shouldTGetExceptionWhenNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name must be not null or empty");

        sut = builderSut.UUID(null).name("").accounts(ACCOUNT_STUB).build();
    }
}
