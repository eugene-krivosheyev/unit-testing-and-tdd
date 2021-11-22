package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {

    @DisplayName("Test case")
    public void shouldStoreAccountWhenAccountAdded() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        final int accountId = 0;
        final double amount = 5.5;
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        Account account = new SavingAccount(accountId, sut, amount);
        assumeTrue(account != null);
        sut.addAccount(account);
        //endregion

        //region Then
        //assertThat(sut, prope
        //        );
        // How to get private property, and Match contains??

        assertTrue(sut.checkAccountIsOwnedBy(account));
        //return accounts.contains(possibleAccount);
        //
    }

    @Test //@Disabled("temporary disabled")
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
        // Logic AND - all checks must pass
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
//        try {
//            new Client();
//        } catch () {
//
//        }
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

        //assertThrows(IllegalArgumentException.class, () -> new Client(id, dummyname) );
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(id, dummyname)
        );

        assertTrue(thrown.getMessage().contains("Client should not be Null"));

        // Lambda returns void and has no parameters ()->{}
        // compiler understands that we expect Executable interface

        // end region
    }



    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
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
    public void shouldNotCreateWhenIdIsNegative() {
        // region 1: Given
        final String validClientName = "test";
        final int id = -1;
        // end region

        // region 2: When
        // end region

        // region 3: Then

        assertThrows(IllegalArgumentException.class, () -> new Client(id, validClientName));
        // end region
    }

    @Test
//    @ParameterizedTest
//    @ValueSource()
    // But this makes an illusion of different program Flow / Branches,
    // but it's actually Expects the same result (Fail or Not Fail) - so it's can be different
    // and it's practically to do a separate test to test different Branches
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
        //assertEquals(obj1, obj2);
        //assertSame(obj1, obj2);

        // Is all those assets cover all cases in domain?
        // We need to combine multiple asserts into one, and 1 function-declaration
        // for example we need to compare 2 xml - we don't have an atomic assertion for that
        // we need easy method to create custom assertion



        //if (??) fail();
        // end region
    }

//    Class NullNameAction implements Executable {
//        @Override
//        public void execute() throws Throwable {
//            new Client(id:1, name:null);
//        }
//    }
}

