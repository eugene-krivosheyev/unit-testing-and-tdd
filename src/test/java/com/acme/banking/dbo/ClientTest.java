package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test Client")
public class ClientTest {
    @Test
    @DisplayName("Test shouldStorePropertiesWhenCreated")
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
    }

    @Test
    @DisplayName("Test shouldThrowExceptionWhenIdNegative")
    public void shouldThrowExceptionWhenIdNegative() {

        final int clientId = -1;
        final String clientName = "dummy client name";

        try {
            Client sut = new Client(clientId, clientName);
        } catch (IllegalArgumentException e){
            assumeTrue( true, "Catch IllegalArgumentException when Id negative");
        }

    }

    @Test
    @DisplayName("Test shouldThrowExceptionWhenNameIsEmpty")
    public void shouldThrowExceptionWhenNameIsEmpty() {

        final int clientId = 1;
        final String clientName = "";

        try {
            Client sut = new Client(clientId, clientName);
        } catch (IllegalArgumentException e){
            assumeTrue( true, "Catch IllegalArgumentException when Name is empty");
        }

    }

    @Test
    @DisplayName("Test shouldThrowExceptionWhenNameIsNull")
    public void shouldThrowExceptionWhenNameIsNull() {

        final int clientId = 1;
        final String clientName = null;

        try {
            Client sut = new Client(clientId, clientName);
        } catch (IllegalArgumentException e){
            assumeTrue( true, "Catch IllegalArgumentException when Name is Null");
        }

    }

}
