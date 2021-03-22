package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
//@ExtendWith(MockitoExtension.class)
public class ClientTest {
//    @Mock Object stub;

    @Test
//    @Disabled //@Ignore
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
        Assertions.assertNotEquals(.3, .1 + .2, "Surprise!!!");
        Assertions.assertSame("a", "a"); // o1 == o2

        //комбинатор
        assertAll("Client successfully store its properties",
//                noName() { assertEquals(clientId, sut.getId()) }
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest: matchers
        assertThat(sut,
                allOf( //anyOf
                        not(hasProperty("id", nullValue())),
                        hasProperty("id", equalTo(clientId)),
                        hasProperty("name", is(clientName))
                ));

        //AssertJ: fluent API
        org.assertj.core.api.Assertions.assertThat(sut)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }

    @Test
    public void shouldGetErrorWhenCreateWithNullName() {
        final int DUMMY_ID = 1;

        final IllegalArgumentException expectedException = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(DUMMY_ID, null)
        );

        assertEquals("name == null", expectedException.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenIdIsZero() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Client(0, "dummy name"));
        assertThat(e.getMessage(), is("id <= 0"));
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNegative() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Client(-1, "dummy name"));
        assertThat(e.getMessage(), is("id <= 0"));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNull() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Client(1, null));
        assertThat(e.getMessage(), is("name == null"));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Client(1, ""));
        assertThat(e.getMessage(), is("name.isEmpty()"));
    }

    @Test
    public void shouldThrowExceptionWhenNameContainsIllegalCharacters() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Client(1, "123"));
        assertThat(e.getMessage(), is("!name.matches('^[a-zA-Z- ]+$')"));
    }
}
