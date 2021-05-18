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
    public void shouldThrowWhenClientIdLessZero(){
        //given
        int clientId = -1;
        String clientName = "Client Name";

        //when
        //then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName),
                "Expected IllegalArgumentException to be thrown if ClientId is less than 0.");
        assertEquals(thrown.getMessage(), "Client ID is less than 0.");
    }

    @Test
    public void shouldThrowWhenClientNameIsNull(){
        //given
        int clientId = 1;
        String clientName = null;

        //when
        //then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName),
                "Expected IllegalArgumentException to be thrown if ClientName is NULL.");
        assertEquals(thrown.getMessage(), "Client Name is null.");
    }

    @Test
    public void shouldThrowWhenClientNameIsEmpty(){
        //given
        int clientId = 1;
        String clientName = "";

        //when
        //then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName),
                "Expected IllegalArgumentException to be thrown if ClientName is empty.");
        assertEquals(thrown.getMessage(), "Client Name is empty.");
    }

    @Test
    public void shouldSetClientIdNameWhenValid(){
        //given
        int clientId = 1;
        String clientName = "Client Name #1";

        //when
        Client sut = new Client(clientId, clientName);
        //then
        assertEquals(sut.getId(), clientId);
        assertEquals(sut.getName(), clientName);
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
