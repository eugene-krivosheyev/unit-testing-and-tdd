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
    public void shouldStorePropertiesWhenCorrectParameters(){
        Client sut = new Client(0, "name");
        assertAll(
                () -> assertEquals(0, sut.getId()),
                () -> assertEquals("name", sut.getName())
        );
    }

    @Test
    public void shouldThrowIllegalExceptionWhenNegativeId() {
        assertThrows(IllegalArgumentException.class,() -> new Client(-1, "name"));
    }

    @Test
    public void shouldThrowIllegalExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class,() -> new Client(5, ""));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNameIsNull() {
        assertThrows(NullPointerException.class,() -> new Client(12, null));
    }

}
