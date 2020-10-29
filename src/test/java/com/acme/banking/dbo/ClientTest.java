package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;
import static org.junit.Assert.*;

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Test
    public void shouldClientNormallyCreatedWhenCorrectParams() {
        Client sut = new Client(ID_STUB, "example");

        Assert.assertEquals(ID_STUB, sut.getId());
        Assert.assertEquals("example", sut.getName());
    }


    @Test
    public void shouldNotCreateWhenIdIsNull() {
        //region when
        try {
            Client sut = new Client(null, "dummy");
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("id is Null", e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        //region when
        try {
            Client sut = new Client(ID_STUB, null);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("name is Null", e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
        //region when
        try {
            Client sut = new Client(ID_STUB, "");
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("name is Empty", e.getMessage());
        }
    }
}
