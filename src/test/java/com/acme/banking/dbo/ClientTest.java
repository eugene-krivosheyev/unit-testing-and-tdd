package com.acme.banking.dbo;

import java.util.Random;

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

    private final Random RANDOM = new Random();

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
    public void shouldStoreIdAndNameWhenNonEmptyNameAndNonNegativeId(){
        //given
        int id = RANDOM.nextInt(2147483647);
        String name = "test";

        //when
        Client sut = new Client(id, name);

        //then
        assertEquals(id, sut.getId());
        assertEquals(name, sut.getName());
    }

    @Test
    public void shouldStoreIdAndNameWhenNonEmptyNameAndIdZero(){
        //given
        int id = 0;
        String name = "test";

        //when
        Client sut = new Client(id, name);

        //then
        assertEquals(id, sut.getId());
        assertEquals(name, sut.getName());
    }

    @Test
    public void shouldGetIllegalArgumentExceptionWhenIdIsNegative(){
        //given
        int id = - (RANDOM.nextInt(2147483647) + 1) ;
        String name = "test";

        //when
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(id, name),
                "Expected new object but was thrown exception"
        );

        //then
        assertTrue(thrown.getMessage().contentEquals("id should not be less than 0"));
    }


    @Test
    public void shouldGetIllegalArgumentExceptionWhenNameIsNull(){
        //given
        int id = RANDOM.nextInt(2147483647);
        String name = null;

        //when
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(id, name),
                "Expected new object but was thrown exception"
        );

        //then
        assertTrue(thrown.getMessage().contentEquals("name should not be null or empty"));
    }

    @Test
    public void shouldGetIllegalArgumentExceptionWhenNameIsEmpty(){
        //given
        int id = RANDOM.nextInt(2147483647);
        String name = "";

        //when
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(id, name),
                "Expected new object but was thrown exception"
        );

        //then
        assertTrue(thrown.getMessage().contentEquals("name should not be null or empty"));
    }

}
