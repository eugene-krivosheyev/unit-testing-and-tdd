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

    private String name;
    private UUID id;

    @Before
    public void setUp() {
        name = "Some client name";
        id = UUID.randomUUID();
    }

    @Test
    public void shouldNotCreatedWhenPassNullId() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Id is null");
        final UUID idNull = null;

        new Client(idNull, name);
    }

    @Test
    public void shouldNotCreatedWhenPassNullName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Name is null");
        final String nameNull = null;

        new Client(id, nameNull);
    }

    @Test
    public void shouldNotCreatedWhenPassBlankName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Name is blank");
        final String nameBlank = "   ";

        new Client(id, nameBlank);
    }

    @Test
    public void shouldCreateClientWhenPassValidIdAndName() {
        Client sut = new Client(id, name);

        assertEquals(id, sut.getId());
        assertEquals(name, sut.getName());
    }
}
