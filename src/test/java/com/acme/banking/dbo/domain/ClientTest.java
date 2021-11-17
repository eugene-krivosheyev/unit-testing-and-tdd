package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {
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

    }

    @Test
    public void shouldThrowWhenNameIsNull() {
        // region 1: Given
        final String dummy = null;
        final int id = 0;
        // end region

        // region 2: When

        // end region

        // region 3: Then
        assertThrows(IllegalArgumentException.class,  ()->new Client(id, dummy) );
        // end region
    }

    @Test
    public void shouldThrowWhenNameIsEmpty() {
        // region 1: Given
        final String dummy = "";
        final int id = 0;
        // end region

        // region 2: When
        // end region

        // region 3: Then
        assertThrows(IllegalArgumentException.class, () -> new Client(id, dummy));
        // end region
    }


    @Test
    public void shouldNotThrowWhenNameValid() {
        // region 1: Given
        final String dummy = "test";
        final int id = 0;
        // end region

        // region 2: When
        // end region

        // region 3: Then
        assertDoesNotThrow(()->new Client(id, dummy));
        // end region
    }
}
