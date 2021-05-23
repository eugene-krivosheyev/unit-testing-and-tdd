package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("Client test")
public class ClientTest {

    @Test
    public void shouldNotCreateClientWhenIdIsNegative() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(-1, "Name"));
        assertEquals(thrown.getMessage(), "id!");
    }

    @Test
    public void shouldNotCreateClientWhenNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(20, null));
        assertEquals(thrown.getMessage(), "name!");
    }

    @Test
    public void shouldNotCreateClientWhenNameIsEmpty() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(20, new String()));
        assertEquals(thrown.getMessage(), "name!");
    }

    @Test
    public void shouldStoreClientPropertiesWhenCreated() {
        int id = 1;
        String name = "Test Client";
        Client client = new Client(id, name);
        assertAll("SavingAccount store its properties",
                () -> assertEquals(id, client.getId()),
                () -> assertEquals(name, client.getName())
        );
    }

    @Test
    @Disabled("temporary disabled")
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        /*
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", is(clientName))
        ));
        */
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(clientId)),
                        hasProperty("name", is(clientName))
                ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
//                .isNotNull().hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }
}
