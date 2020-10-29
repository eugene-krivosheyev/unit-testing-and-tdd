package com.acme.banking.dbo;

import com.acme.banking.dbo.Factories.ClientBuilder;
import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;
import static org.junit.Assert.*;

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldClientNormallyCreatedWhenCorrectParams() {
        Client sut = new ClientBuilder()
                .SetId(ID_STUB)
                .SetName("example")
                .Build();

        Assert.assertEquals(ID_STUB, sut.getId());
        Assert.assertEquals("example", sut.getName());
    }

    @Test
    public void shouldNotCreateWhenIdIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is Nul");

        Client sut = new ClientBuilder()
                .SetId(null)
                .SetName("dummy")
                .Build();
    }

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is Null");

        Client sut = new ClientBuilder()
                .SetId(ID_STUB)
                .SetName(null)
                .Build();
    }

    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is Empty");

        Client sut = new ClientBuilder()
                .SetId(ID_STUB)
                .SetName("")
                .Build();
    }
}
