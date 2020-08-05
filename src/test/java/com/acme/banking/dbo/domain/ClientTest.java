package com.acme.banking.dbo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class ClientTest {
    public static final UUID CLIENT_UUID = UUID.fromString("75852e48-dd5e-4062-921d-bae2bd91a045");
    public static final String DUMMY_NAME = "dummy name";

    @Test
    public void shouldSavePropertiesWhenClientCreated() {
        Client sut = new Client(CLIENT_UUID, DUMMY_NAME);

        assertEquals(CLIENT_UUID, sut.getId());
        assertEquals(DUMMY_NAME, sut.getName());
//        assertThat(sut.getId(),
//            allOf(
//                equalTo(stubId),
//                notNullValue()
//        ));
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldNotCreateClientWhenIdIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("UUID can`t be null");

        Client sut = new Client(null, DUMMY_NAME);
    }

    @Test
    public void shouldNotCreateClientWhenNameIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Name can`t be null or empty");

        Client sut = new Client(CLIENT_UUID, null);
    }

    @Test
    public void shouldNotCreateClientWhenNameIsEmpty() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Name can`t be null or empty");

        Client sut = new Client(CLIENT_UUID, " ");
    }
}
