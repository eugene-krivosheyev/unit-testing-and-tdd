package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.beans.HasPropertyWithValue;
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewClientWhenValidArguments() {
        Client sut = new Client(DUMMY_ID, DUMMY_NAME);

        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(DUMMY_ID)),
                        hasProperty("name", equalTo(DUMMY_NAME))
                )
        );
    }

    @Test
    public void shouldNotCreateWhenClientWithoutId() {
        thrown.expect(IllegalArgumentException.class);

        new Client(null, DUMMY_NAME);
    }

    @Test
    public void shouldNotCreateWhenClientWithoutName() {
        thrown.expect(IllegalArgumentException.class);

        new Client(DUMMY_ID, null);
    }

    @Test
    public void shouldNotCreateWhenClientWithEmptyName() {
        thrown.expect(IllegalArgumentException.class);

        new Client(DUMMY_ID, "");
    }
}
