package com.acme.banking.dbo.domain;

import org.junit.Test;

import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClientTest {

    @Test
    public void shouldCreateNewClient_when_constructorArgumentsAreValid() {
        UUID id = UUID.randomUUID();
        String name = randomAlphabetic(32);

        Client actual = new Client(id, name);

        assertNotNull(actual);
        assertEquals(id, actual.getId());
        assertEquals(name, actual.getName());
    }

    @Test
    public void shouldThrowIllegalArgumentException_when_sutConstructorCalledWithNullId() {
        UUID id = null;
        String name = randomAlphabetic(32);

        assertThatThrownBy(() -> new Client(id, name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("id must not be null");
    }

    @Test
    public void shouldThrowIllegalArgumentException_when_sutConstructorCalledWithNullName() {
        UUID id = UUID.randomUUID();
        String name = null;

        assertThatThrownBy(() -> new Client(id, name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("name not must be blank");
    }

    @Test
    public void shouldThrowIllegalArgumentException_when_sutConstructorCalledWithEmptyStringName() {
        UUID id = UUID.randomUUID();
        String name = "";

        assertThatThrownBy(() -> new Client(id, name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("name not must be blank");
    }

    @Test
    public void shouldThrowIllegalArgumentException_when_sutConstructorCalledWithWhiteSpaceName() {
        UUID id = UUID.randomUUID();
        String name = " ";

        assertThatThrownBy(() -> new Client(id, name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("name not must be blank");
    }
}