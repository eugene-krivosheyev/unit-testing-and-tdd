package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

public class ClientTest {
    private final UUID ID_STUB = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");

    @Test
    public void shouldStorePropertiesWhenCreated() {
        Client sut = new Client(ID_STUB, "Dummy");

        assertThat(sut,
            allOf(

                hasProperty("id", equalTo(ID_STUB)),
                hasProperty("name", is("Dummy"))
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void clientIdShouldBeEqual36Symbols() {
        new Client(
                UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaTOCUT"), "Dummy"
        );
    }

    @Test
    public void clientNameShouldStartsWithCapitalLetterAndEndsWithLowercaseLetter() {
        Client sut = new Client(
                ID_STUB, "Dummy"
        );

        assertThat(sut,
                allOf(
                        hasProperty("name", startsWith("D")),
                        hasProperty("name", endsWith("y"))
                ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void clientNameShouldBeLongerThen1Letter() {
        Client sut = new Client(
                ID_STUB, "D"
        );
    }

}
