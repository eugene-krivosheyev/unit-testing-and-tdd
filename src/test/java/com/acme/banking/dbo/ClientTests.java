package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.beans.HasPropertyWithValue.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ClientTests {
    private final UUID DUMMY_ID = UUID.randomUUID();
    private final String DUMMY_NAME = "DUMMY_NAME";
    private Client sut;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewClientWhenValidArguments() {
        sut = ClientBuilder.builder()
                .withId(DUMMY_ID)
                .withName(DUMMY_NAME)
                .build();

        assertThat(
                sut,
                allOf(
                        hasProperty("id", equalTo(DUMMY_ID)),
                        hasProperty("name", equalTo(DUMMY_NAME))
                )
        );
    }

    @Test
    public void shouldNotCreateWhenClientWithoutId() {
        thrown.expect(IllegalArgumentException.class);

        sut = ClientBuilder.builder()
                .withId(null)
                .withName(DUMMY_NAME)
                .build();
    }

    @Test
    public void shouldNotCreateWhenClientWithoutName() {
        thrown.expect(IllegalArgumentException.class);

        sut = ClientBuilder.builder()
                .withId(DUMMY_ID)
                .withName(null)
                .build();
    }

    @Test
    public void shouldNotCreateWhenClientWithEmptyName() {
        thrown.expect(IllegalArgumentException.class);

        sut = ClientBuilder.builder()
                .withId(DUMMY_ID)
                .withName("")
                .build();
    }
}
