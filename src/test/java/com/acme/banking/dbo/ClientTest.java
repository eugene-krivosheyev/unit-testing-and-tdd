package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("Test suite")
public class ClientTest extends AbstractTest {
    @ParameterizedTest
    @MethodSource("guardClausesParams")
    public void shouldNotBeCreatedWhenNegativeId(final ClientTestParams params) {
        // region Given
        final Executable producer = () -> new Client(params.getId(), params.getName());
        // endregion

        assertThrowIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, params.getExceptionMessage());
    }

    private static Stream<ClientTestParams> guardClausesParams() {
        return Stream.of(
                new ClientTestParams(-1, null, "ID is expected to be positive int"),
                new ClientTestParams(1, null, "Name is NULL"),
                new ClientTestParams(1, "", "Name is empty")
        );
    }

    @Test
    public void getFieldsWhenCreatedCorrectlyAndRequested() {
        // region Given
        final int expectedId = 1;
        final String expectedName = "Test Name";
        final Client client = new Client(expectedId, expectedName);
        // endregion

        // region When
        final int id = client.getId();
        final String name = client.getName();
        // endregion

        // region Then
        assertAll(
                () -> assertEquals(expectedId, id, "Getter for 'id' seems isn't working"),
                () -> assertEquals(expectedName, name, "Getter for 'name' seems isn't working")
        );

        MatcherAssert.assertThat(client,
                Matchers.allOf(
                        Matchers.hasProperty("id", equalTo(expectedId)),
                        Matchers.hasProperty("name", equalTo(expectedName))
                )
        );

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(id).isEqualTo(expectedId);
        softly.assertThat(name).isEqualTo(expectedName);
        softly.assertAll();
        // endregion
    }

    @Test
    public void shouldGetAccountsWhenGetterCalled() {
        Account dummyAccount = mock(Account.class);

        Client testClient = new Client(1, "Test name");
        when(dummyAccount.getClient()).thenReturn(testClient);
        testClient.addAccount(dummyAccount);

        Assertions.assertThat(testClient.getAccounts())
                .hasSize(1)
                .contains(dummyAccount);
    }

    @Test
    public void shouldNotAddAccountWhenClientIsNull() {
        Account dummyAccount = mock(Account.class);
        when(dummyAccount.getClient()).thenReturn(null);

        Client testClient = new Client(1, "Test name");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> testClient.addAccount(dummyAccount));

        Assertions.assertThat(thrown.getMessage()).isEqualTo("No client in the account");
    }

    @Test
    public void shouldNotAddAccountWhenClientIdIsDifferent() {
        Client accountClientStub = mock(Client.class);
        when(accountClientStub.getId()).thenReturn(2);
        Account dummyAccount = mock(Account.class);
        when(dummyAccount.getClient()).thenReturn(accountClientStub);

        Client testClient = new Client(1, "Test name");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> testClient.addAccount(dummyAccount));

        Assertions.assertThat(thrown.getMessage()).isEqualTo("Client Id is not of this client");
    }

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
}

class ClientTestParams {
    final private int id;
    final private String name;
    final private String exceptionMessage;

    public ClientTestParams(int id, String name, String exceptionMessage) {
        this.id = id;
        this.name = name;
        this.exceptionMessage = exceptionMessage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
