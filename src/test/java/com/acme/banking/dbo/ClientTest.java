package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.notification.StoppedByUserException;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;


/**
 * Test Case
 */
public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Rule
    public ExpectedException exception = ExpectedException.none();

//    @Test
//    public void shouldStorePropertiesWhenCreated() {
//        //region when
//        Client sut = new Client(ID_STUB, "dummy client name");
//
//        //endregion
//
//        //region then
//        assertThat(sut,
//            allOf(
//                hasProperty("id", notNullValue()),
//                hasProperty("id", equalTo(ID_STUB)),
//                hasProperty("name", is("dummy client name"))
//        ));
//        //endregion
//    }
//
//    @Test
//    public void shouldNotCreateClientWhenNullId() {
//        //region Given
//        UUID dummy = null;
//        //endregion
//
//        //region When
//        Client sut;
//        //endregion
//
//        //region Then
//        try {
//            sut = new Client(dummy, "Jhon Doe");
//            assertFalse("SUT not null", true);
//        } catch (IllegalArgumentException ex) {
//            assertEquals("id", ex.getMessage());
//        }
//        //endregion
//    }
//
//    @Test
//    public void shouldNotCreateClientWhenNullName() {
//        //region Given
//        UUID id = ID_STUB;
//        String name = null;
//        //endregion
//
//        //region When
//        Client sut;
//        //endregion
//
//        //region Then
//        try {
//            sut =new Client(id, name);
//            assertFalse("SUT not null", true);
//        } catch (IllegalArgumentException ex) {
//            assertEquals("name", ex.getMessage());
//        }
//        //endregion
//    }
//
//    @Test
//    public void shouldNotCreateClientWhenEmptyName() {
//        //region Given
//        UUID id = ID_STUB;
//        String name = "";
//        //endregion
//
//        //region When
//        Client sut;
//        //endregion
//
//        //region Then
//        try {
//            sut =new Client(id, name);
//            assertFalse("SUT not null", true);
//        } catch (IllegalArgumentException ex) {
//            assertEquals("name", ex.getMessage());
//        }
//        //endregion
//    }

    @Test
    public void shouldNotCreateClientWhenEmptyAccounts() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("accounts is null");

        //region Given
        UUID dummyId = ID_STUB;
        String dummyName = "dummy";
        Collection<Account> accounts = null;
        //endregion

        //region When
        Client sut;
        //endregion

        //region Then
        sut = new ClientBuilder()
                .withId(dummyId) //accumulate
                .withName(dummyName) //accumulate
                .withAccounts(accounts) //accumulate
                .build(); //new | mock()
        //endregion
    }
}
