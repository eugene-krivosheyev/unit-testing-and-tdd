package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


@DisplayName("Test suite")
public class ClientTest {


    private static final int CORRECT_ID = 1;
    private static final int DUMMY_ID = 2;
    private static final int INVALID_ID = -1;
    private static final int ZERO_ID = 0;
    private static final String CORRECT_NAME = "Test";
    private static final String DUMMY_NAME = "Dummy name";

    @Test
    @Disabled("temporary disabled")
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {

        //region when
        Client sut = new Client(CORRECT_ID, CORRECT_NAME);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(CORRECT_ID, sut.getId()),
                () -> assertEquals(CORRECT_NAME, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(CORRECT_ID)),
                        hasProperty("name", is(CORRECT_NAME))
                ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", CORRECT_ID)
                .hasFieldOrPropertyWithValue("name", CORRECT_NAME);
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @Test
    public void shouldNotCreateWithZeroId() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new Client(ZERO_ID, DUMMY_NAME))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id shouldn't be 0 or negative");

    }

    @Test
    public void shouldNotCreateWithNegativeId() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new Client(INVALID_ID, DUMMY_NAME))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id shouldn't be 0 or negative");

    }

    @Test
    public void shouldNotCreateWithBlankName() {
        final String clientName = " ";

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new Client(DUMMY_ID, clientName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name shouldn't be blank");

    }

    @Test
    public void shouldNotCreateWithNullName() {
        final String clientName = null;

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new Client(DUMMY_ID, clientName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name shouldn't be null");
    }

    @Test
    public void shouldNotCreateWithEmptyName() {
        final String clientName = "";
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new Client(DUMMY_ID, clientName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name shouldn't be empty");
    }
}
