package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {
    @Test
    @Disabled("temporary disabled")
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
    void shouldCreateClientWhenInputDataIsCorrect() {
        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);

        assertAll("Should create client when input data is correct",
                () -> assertNotNull(sut),
                () -> assertEquals(1, sut.getId()),
                () -> assertEquals("dummy client name", sut.getName())
        );
    }

    @Test
    void shouldThrowExceptionDuringCreationWhenIdLessThanOne() {
        final int invalidClientId = 0;
        final String dummyClientName = "dummy client name";

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(invalidClientId, dummyClientName)
        );

        assertAll("Create client fail: clientId is less than one",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Id should be grater than zero!", thrown.getMessage())
        );
    }

    @Test
    void shouldThrowExceptionDuringCreationWhenNameIsNull() {
        final int dummyClientId = 1;
        final String invalidClientName = null;

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummyClientId, invalidClientName)
        );

        assertAll("Create client fail: clientName is null",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Name shouldn't be null!", thrown.getMessage())
        );
    }

    @Test
    void shouldThrowExceptionDuringCreationWhenNameIsEmpty() {
        final int dummyClientId = 1;
        final String invalidClientName = "";

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummyClientId, invalidClientName)
        );

        assertAll("Create client fail: clientName is empty",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Name shouldn't be empty!", thrown.getMessage())
        );
    }

    @Test
    void clientShouldHasAccountWitchWasAdded() {
        final int dummyClientId = 1;
        final String dummyClientName = "dummy client name";
        final Client dummyClient = new Client(dummyClientId, dummyClientName);
        final double dummyAccountAmount = 1000.0;
        final int dummyAccountId = 1;
        final Account account = new SavingAccount(dummyAccountId, dummyClient, dummyAccountAmount);

        dummyClient.addAccount(account);

        Optional<Account> result = dummyClient.getAccounts().stream().findAny();

        assertAll("Client should has account witch was added",
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(account, result.get())
        );
    }
}
