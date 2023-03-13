package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Client(-1, "name"));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Client(1, null));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Client(1, "    "));
    }

    @Test
    public void shouldCreateNewClientSuccessfully() {
        Client client = new Client(1, "name");
        assertEquals(1, client.getId());
        assertEquals("name", client.getName());

    }


}
