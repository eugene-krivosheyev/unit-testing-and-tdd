package com.acme.banking.dbo.domain;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.acme.banking.dbo.domain.Client;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    @Test
    void clientIdShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Client(null, ""));
    }

    @Test
    void clientNameShouldNotBeEmtpy() {
        assertThrows(IllegalArgumentException.class, () -> new Client(UUID.randomUUID(), ""));
    }

    @Test
    void clientNameShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Client(UUID.randomUUID(), null));
    }

    @Test
    void validClientCreationPerforms() throws IllegalArgumentException {
        UUID uuid = UUID.randomUUID();
        Client sut = new Client(uuid, "Client name");

        assertThat(uuid.equals(sut.getId()));
        assertThat("Client name".equals(sut.getName()));
    }

    @Test
    void invalidClientNameNotPerforms() throws IllegalArgumentException {
        UUID uuid = UUID.randomUUID();
        Client sut = new Client(uuid, "Client name");

        assertThat(uuid.equals(sut.getId()));
        assertThat("Client name".equals(sut.getName()));
    }
}