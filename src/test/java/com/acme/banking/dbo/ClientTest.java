package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Test
    public void shouldClientNormallyCreatedWhenCorrectParams() {
        //region when
        Client sut = new Client(ID_STUB, "example");
        //endregion

        //region then

        Assert.assertEquals(ID_STUB, sut.getId());
        Assert.assertEquals("example", sut.getName());
        //endregion
    }


    @Test
    public void shouldNotCreateWhenIdIsNull() {
        boolean valid = true;
        //region when
        try
        {
        Client sut = new Client(null, "dummy");
        assertFalse(true);
        }catch (IllegalArgumentException e)
        {
            assertEquals("id is Null" , e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        boolean valid = true;
        //region when
        try
        {
            Client sut = new Client(ID_STUB, null);
            assertFalse(true);
        }catch (IllegalArgumentException e)
        {
            assertEquals("name is Null" , e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
        boolean valid = true;
        //region when
        try {
            Client sut = new Client(ID_STUB, "");
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("name is Empty" , e.getMessage());
        }
    }
}
