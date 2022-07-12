package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;


@DisplayName("Unit client tests")
public class UnitClientTest {

    private final String DUMMY_CLIENT_NAME = "dummy client name";
    private final String EMPTY_CLIENT_NAME = "";
    private final String NULL_CLIENT_NAME = null;
    private final int DUMMY_ID = 1;
    private final int ZERO_ID = 0;


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
        Client sut = new Client(DUMMY_ID, DUMMY_CLIENT_NAME);

        assertAll("Should create client when input data is correct",
                () -> assertNotNull(sut),
                () -> assertEquals(1, sut.getId()),
                () -> assertEquals("dummy client name", sut.getName())
        );
    }

    @Test
    void shouldThrowExceptionDuringCreationWhenIdLessThanOne() {
        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(ZERO_ID, DUMMY_CLIENT_NAME)
        );

        assertAll("Create client fail: clientId is less than one",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Id should be grater than zero!", thrown.getMessage())
        );
    }

    @Test
    void shouldThrowExceptionDuringCreationWhenNameIsNull() {
        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(DUMMY_ID, NULL_CLIENT_NAME)
        );

        assertAll("Create client fail: clientName is null",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Name shouldn't be null!", thrown.getMessage())
        );
    }

    @Test
    void shouldThrowExceptionDuringCreationWhenNameIsEmpty() {
        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(DUMMY_ID, EMPTY_CLIENT_NAME)
        );

        assertAll("Create client fail: clientName is empty",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Name shouldn't be empty!", thrown.getMessage())
        );
    }

    @Test
    void clientShouldHasAccountWitchWasAdded() {
        final Client sut = new Client(DUMMY_ID, DUMMY_CLIENT_NAME);
        Account accountStub = new MockitoAccountBuilder().withClient(sut).build();

        sut.addAccount(accountStub);

        Optional<Account> result = sut.getAccounts().stream().findAny();

        assertAll("Client should has account witch was added",
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(accountStub, result.get()),
                () -> verify(accountStub, times(1)).getClient()
        );
    }

    @Test
    void shouldThrowExceptionWhenAccountHasInvalidClient() {
        final Client sut = new Client(DUMMY_ID, DUMMY_CLIENT_NAME);
        Account accountStub = new MockitoAccountBuilder().withClient(null).build();

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sut.addAccount(accountStub)
        );

        assertAll("Should throw exception when account has invalid client",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Account should have correct client!", thrown.getMessage())
        );
    }
}
