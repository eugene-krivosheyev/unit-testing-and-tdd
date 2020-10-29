package com.acme.banking.dbo.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        thrown.expect(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreatedWhenPassNullId() {
        thrown.expectMessage("Id is null");
        final UUID idNull = null;
        final String name = "Some client name";

        new Client(idNull, name);
    }

    @Test
    public void shouldNotCreatedWhenPassNullName() {
        thrown.expectMessage("Name is null");
        final UUID id = UUID.randomUUID();
        final String nameNull = null;

        new Client(id, nameNull);
    }

    @Test
    public void shouldNotCreatedWhenPassBlankName() {
        thrown.expectMessage("Name is blank");
        final UUID id = UUID.randomUUID();
        final String nameBlank = "   ";

        new Client(id, nameBlank);
    }

    @Test
    public void shouldCreateClientWhenPassValidIdAndName() {
        //TODO тут есть проблемы с рулами
        final UUID uuid = UUID.randomUUID();
        final String name = "Some client name";

        Client sut = new Client(uuid, name);

        assertEquals(uuid, sut.getId());
        assertEquals(name, sut.getName());
    }
}
