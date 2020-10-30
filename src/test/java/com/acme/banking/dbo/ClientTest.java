package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.notification.StoppedByUserException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


/**
 * Test Case
 */
public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        Client sut = new ClientBuilder()
                .build(); //new | mock()
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("name", notNullValue()),
                        hasProperty("name", is("dummy")),
                        hasProperty("accounts", notNullValue())
                ));
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNullId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");

        //region Then
        Client sut = new ClientBuilder()
                .withId(null) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNullName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null");

        //region Then
        Client sut = new ClientBuilder()
                .withName(null) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenEmptyName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is empty");

        //region Then
        Client sut = new ClientBuilder()
                .withName("") //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNullAccounts() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("accounts is null");

        //region Then
        Client sut = new ClientBuilder()
                .withAccounts(null) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldCreateClientWhenEmptyAccounts() {
        //region Then
        Client sut = new ClientBuilder()
                .withAccounts(Collections.EMPTY_LIST) //accumulate
                .build(); //new | mock()
        //endregion
    }

    @Test
    public void shouldStoreOneAccountWhenCreateClientWithOneAccount() {
        Account dummyAccount = mock(Account.class);

        //region Then
        Client sut = new ClientBuilder()
                .withAccounts(Collections.singletonList(dummyAccount)) //accumulate
                .build(); //new | mock()
        //endregion

        Assert.assertEquals(dummyAccount, sut.getAccounts().toArray()[0]);
    }

    @Test
    public void shouldStoreTwoAccountWhenCreateClientWithTwoAccount() {
        //Given
        Collection<Account> dummyCollectionAccounts = Arrays.asList(mock(Account.class), mock(Account.class));

        //When
        Client sut = new ClientBuilder()
                .withAccounts(dummyCollectionAccounts)//accumulate
                .build(); //new | mock()
        //Then
        Assert.assertEquals(dummyCollectionAccounts, sut.getAccounts());
    }

    //    Object objectStub = mock(Object.class);
//
//    when(objectStub.toString())
//    thenReturn("stub string")
//        .
//
//    thenReturn("1")
//        .
//
//    thenThrow(new RuntimeException("ex"));
//    final ArrayList<Object> sut = new ArrayList<>();
//sut.add(objectStub);
//    final String stringRepresentation = sut.toString();
//
//    assertTrue(stringRepresentation.contains("stub string"));
//System.out.println(objectStub.toString());
//System.out.println(objectStub.toString());
}
