package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

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
    public void shouldReturnExceptionWhenNameIsNull() {
        //region 1: Fixture | Arrange | Given
        final String nullName = new String();
        final int dummyId = 1;
        final String expectedMessage = "Name is empty.";
        //endregion

        //region 2: Act | When
        try {
            new Client(dummyId, nullName);
        } catch (IllegalArgumentException thrown) {
            assertEquals( expectedMessage, thrown.getMessage());
        }

    }

    @Test
    public void shouldReturnExceptionWhenNameIsEmpty() {
        //region 1: Fixture | Arrange | Given
        final String emptyName = "";
        final int dummyId = 1;
        final String expectedMessage = "Name is empty.";
        //endregion

        //region 2: Act | When
        try {
            new Client(dummyId, emptyName);
        } catch (IllegalArgumentException thrown) {
            assertEquals( expectedMessage, thrown.getMessage());
        }

    }

    @Test
    public void shouldReturnExceptionWhenIdIsNegative() {
        //region 1: Fixture | Arrange | Given
        final String dummyName = "aaa";
        final int negativeId = -1;
        final String expectedMessage = "Id is negative";
        //endregion

        //region 2: Act | When
        try {
            new Client(negativeId, dummyName);
        } catch (IllegalArgumentException thrown) {
            assertEquals( expectedMessage, thrown.getMessage());
        }

    }
}
