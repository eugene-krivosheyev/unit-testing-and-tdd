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
    public void shouldNotCreatedWhenIdIsNegaitve(){
        int negativeId = -1;
        String dummyName = "Dummy name";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(negativeId, dummyName),
                "Expected IllegalArgumentException to be thrown if ClientId is less than 0.");
        assertEquals(thrown.getMessage(), "Client ID is less than 0.");
    }

    @Test
    public void shouldNotCreatedWhenNameIsNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(1, null),
                "Expected IllegalArgumentException to be thrown if ClientName is NULL.");
        assertEquals(thrown.getMessage(), "Client Name is null.");
    }

    @Test
    public void shouldNotCreatedWhenNameIsEmpty(){
        int dummyId = 1;
        String emptyName = "";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(dummyId, emptyName),
                "Expected IllegalArgumentException to be thrown if ClientName is empty.");
        assertEquals(thrown.getMessage(), "Client Name is empty.");
    }

    @Test
    public void shouldStorePropertiesWhenCreated(){
        int validId = 1;
        String validName = "Client name";
        Client sut = new Client(validId, validName);

        assertEquals(validId, sut.getId());
        assertEquals(validName, sut.getName());
    }

/*
    @Test @Disabled("temporary disabled")
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

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }*/
}
