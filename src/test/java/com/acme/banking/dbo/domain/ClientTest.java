package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
//    public void shouldThrowWhenNameIsNull() {
    public void shouldNotCreateWhenNameIsNull() {
        // region 1: Given
        final String dummyname = null;
        final int id = 0;
        // end region

        // region 2: When
        try {
            new Client()
        } catch () {

        }
        // end region

        // region 3: Then
        //assertThrows(IllegalArgumentException.class, new NullNameAction());

//        assertThrows(IllegalArgumentException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                new Client(id:1, name:null);
//            }
//        });

//        assertThrows(IllegalArgumentException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                new Client(id, dummyname);
//            }
//        });

        assertThrows(IllegalArgumentException.class, () -> new Client(id, dummyname) );
        // Lambda returns void and has no parameters ()->{}
        // compiler understands that we expect Executable interface

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
    public void shouldCreatedWhenNameValid() {
        // We should use name with Domain , not Tech.

        // region 1: Given
        final String validClientName = "test"; //
        final int dummyId = 0;
        // end region

        // region 2: When
        // client = new Client()
        // end region

        // region 3: Then
        assertDoesNotThrow(() -> new Client(dummyId, validClientName));
        // end region
    }

    Class NullNameAction implements Executable {
        @Override
        public void execute() throws Throwable {
            new Client(id:1, name:null);
        }
    }
}

