package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("Client class tests")
public class ClientTest {
    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);

        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAnotherClientAccountAdded() {
        final Client dummyClient = new Client(1, "dummy");
        final Account dummyAccount = new SavingAccount(1, dummyClient, 1.0);
        final Client anotherDummyClient = new Client(2, "dummy");

        Executable sut = () -> anotherDummyClient.addAccount(dummyAccount);

        assertThrows(
                IllegalArgumentException.class,
                sut,
                "The account belongs to another client!");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidClientTestData")
    public void shouldThrowIllegalArgumentExceptionWhenFieldIsInvalid(ClientInvalidTestData invalidData) {
        Executable sut = () -> new Client(invalidData.getId(), invalidData.getName());

        assertThrows(
                IllegalArgumentException.class,
                sut,
                invalidData.getErrorMessage());
    }

    private static Stream<ClientInvalidTestData> provideInvalidClientTestData() {
        return Stream.of(
                new ClientInvalidTestData(-1, "dummy client name", "Client id should be positive!"),
                new ClientInvalidTestData(1, null, "Client name should be not null!"),
                new ClientInvalidTestData(1, "", "Client name should be not empty!")
        );
    }
}

class ClientInvalidTestData {
    private int id;
    private String name;
    private String errorMessage;

    ClientInvalidTestData(int id, String name, String errorMessage) {
        this.id = id;
        this.name = name;
        this.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}