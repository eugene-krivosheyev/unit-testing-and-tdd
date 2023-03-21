package com.acme.banking.dbo.domain;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Test
    void shouldStoreAccountWhenAddAccount() {
        Client client = new Client(1, "1");
        SavingAccount savingAccount = new SavingAccount(1, client, 1);

        client.addAccount(savingAccount);

        assertThat(client.getAccounts()).hasSize(1);
        assertThat(client.getAccounts()).contains(savingAccount);
    }

    @Test
    void shouldThrowErrorWhenAddNullAccount() {
        Client client = new Client(1, "1");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> client.addAccount(null));
    }

    @Test
    void shouldThrowErrorWhenAddAccountWithNotLinkedClient() {
        Client notLinkedClient = new Client(1, "1");
        Client client = new Client(0, "0");

        SavingAccount savingAccount = new SavingAccount(1, notLinkedClient, 1);

        assertThrows(IllegalArgumentException.class, () -> client.addAccount(savingAccount));
    }

    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(true);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        org.hamcrest.MatcherAssert.assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(clientId)),
                        hasProperty("name", is(clientName))
                ));

        //AssertJ:
        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @ParameterizedTest
    @MethodSource
    void shouldThrowErrorWhenInvalidParams(int id, String name) {
        assertThrows(IllegalArgumentException.class, () -> new Client(id, name));
    }

    private static Stream<Arguments> shouldThrowErrorWhenInvalidParams() {
        return Stream.of(
                Arguments.of(-1, "1"),
                Arguments.of(0, ""),
                Arguments.of(0, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCreateWhenValidParams(int validId, String validName) {
        Client client = new Client(validId, validName);

        assertEquals(validId, client.getId());
        assertEquals(validName, client.getName());
    }

    private static Stream<Arguments> shouldCreateWhenValidParams() {
        return Stream.of(
                Arguments.of(0, "1"),
                Arguments.of(1, "12")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shodGetErrorWhenCreateClientWithInvalidName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Client(name));
    }

    @Test
    void shouldCreateWhenValidName() {
        String validName = "testName";
        Client client = new Client(validName);

        assertEquals(validName, client.getName());
    }
}
