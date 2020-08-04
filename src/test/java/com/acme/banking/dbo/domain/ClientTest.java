package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        String name = "name";
        //endregion

        //region when
        Client sut = new Client(stubId, name);
        //endregion

        //region then
        assertNotNull(sut.getId());
        assertNotNull(sut.getName());
        assertEquals(stubId, sut.getId());
        assertEquals(name, sut.getName());
        //endregion
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNameIsNull() {
        UUID stubId = UUID.randomUUID();
        String name = null;
        Client sut = new Client(stubId, name);
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("name cant be null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        UUID stubId = null;
        String name = "name";
        Client sut = new Client(stubId, name);
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("UUID cant be null");
    }
}
