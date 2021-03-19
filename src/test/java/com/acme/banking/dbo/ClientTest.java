package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

public class ClientTest {
    private final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        Client sut = new Client(ID_STUB, "Dummy");
        //endregion

        //region then
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(ID_STUB)),
                hasProperty("name", is("Dummy"))
        ));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void clientIdShouldBeEqual36Symbols() {
        //region when
        Client sut = new Client(
                UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2TOCUT"), "Dummy"
        );
        //endregion

        //region then
        // TODO try to brainstorm correct assert
        //endregion
    }

    @Test
    public void clientNameShouldStartsWithCapitalLetterAndEndsWithLowercaseLetter() {
        //region when
        Client sut = new Client(
                ID_STUB, "Dummy"
        );
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("name", startsWith("[A-Z]")),
                        hasProperty("name", endsWith("[a-z]"))
                ));
        //endregion
    }

    @Test
    public void clientNameShouldBeLongerThen1Letter() {
        //region when
        Client sut = new Client(
                ID_STUB, "Dummy"
        );
        //endregion

        //region then
        assertTrue("Name is less then two letters",sut.getName().length() > 1);
        //endregion
    }

}
