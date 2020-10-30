package com.acme.banking.dbo;

import com.acme.banking.dbo.Factories.ClientBuilder;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class ClientTest {

    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldClientCreatedWhenCorrectParams() {
        Client sut = ClientBuilder.create()
                .build();

        Assert.assertEquals(ID_STUB, sut.getId());
        Assert.assertEquals("dummy", sut.getName());
    }

    @Test
    public void shouldNotCreateWhenIdIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is Nul");

        Client sut = ClientBuilder.create()
                .setId(null)
                .build();
    }

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is Null");

        Client sut = ClientBuilder.create()
                .setName(null)
                .build();
    }

    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is Empty");

        Client sut = ClientBuilder.create()
                .setName("")
                .build();
    }

    @Test
    public void shouldNotCreateWhenAccountsIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("accounts is null");

        Client sut = ClientBuilder.create()
                .setAccounts(null)
                .build();
    }
}
