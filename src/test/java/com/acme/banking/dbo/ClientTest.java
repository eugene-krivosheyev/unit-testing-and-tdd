package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite for client")
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
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @Test
    public void shouldCreateWithIdMoreThanZero() {
        int id = 1;
        String dummyName = "test name";

        assertDoesNotThrow(
                () -> new Client(id, dummyName)
        );
    }

    @Test
    public void shouldNotCreateWithIdEqualsZero() {
        int id = 0;
        String dummyName = "test name";

        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(id, dummyName)
        );
    }

    @Test
    public void shouldNotCreateWithIdLessThanZero() {
        int id = -1;
        String dummyName = "test name";

        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(id, dummyName)
        );
    }

    @Test
    public void shouldCreateWithNameIsNotNullAndEmpty() {
        int dummyId = 1;
        String name = "test name";

        assertDoesNotThrow(
                () -> new Client(dummyId, name)
        );
    }

    @Test
    public void shouldNotCreateWithNameIsNull() {
        int dummyId = 1;
        String name = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummyId, name)
        );
    }

    @Test
    public void shouldNotCreateWithNameIsEmpty() {
        int dummyId = 1;
        String name = "";

        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummyId, name)
        );
    }
}
