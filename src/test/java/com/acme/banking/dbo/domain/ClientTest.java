package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        String name = "test";
        //endregion

        //region when
        Client sut = new Client(stubId, name);
        //endregion

        //region then
        assertNotNull(sut.getId());
        assertNotNull(sut.getName());
        assertEquals(stubId, sut.getId());
        assertEquals(name, sut.getName());
//        assertThat(sut.getId(),
//            allOf(
//                equalTo(stubId),
//                notNullValue()
//        ));
        //endregion
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        UUID stubId = null;
        String name = "test";
        Client sut = new Client(stubId, name);
        expectedException.expectMessage("UUID can`t be null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNameIsNull() {
        UUID stubId = UUID.randomUUID();
        String name = null;
        Client sut = new Client(stubId, name);
        expectedException.expectMessage("Name can`t be null or empty");
    }
}
