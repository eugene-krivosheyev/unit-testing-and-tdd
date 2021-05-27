package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.*;
import static org.assertj.core.api.ThrowableAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Client class tests")
public class ClientTest {
    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeThat(sut).isNotNull();

        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
    }

    @Test
    @Disabled
    public void shouldThrowIllegalArgumentExceptionWhenAnotherClientAccountAdded() {
        final Client dummyClient = new Client(1, "dummy");
        final Account dummyAccount = new SavingAccount(1, dummyClient, 1.0);
        final Client anotherDummyClient = new Client(2, "dummy");

        ThrowingCallable sut = () -> anotherDummyClient.addAccount(dummyAccount);

        assertThatIllegalArgumentException().isThrownBy(sut)
                .withMessageMatching("The account belongs to another client!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAnotherClientAccountAddedUnit() {
        final Client dummyClient = new Client(1, "dummy");
        final Account dummyAccount = mock(Account.class);
        final Client anotherDummyClient = new Client(2, "dummy");
        when(dummyAccount.getClient()).thenReturn(dummyClient);

        Executable sut = () -> anotherDummyClient.addAccount(dummyAccount);

        assertThrows(
                IllegalArgumentException.class,
                sut,
                "The account belongs to another client!");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidClientTestData")
    public void shouldThrowIllegalArgumentExceptionWhenFieldIsInvalid(ClientInvalidTestData invalidData) {
        ThrowingCallable sut = () -> new Client(invalidData.getId(), invalidData.getName());

        assertThatIllegalArgumentException().isThrownBy(sut)
                .withMessageMatching(invalidData.getErrorMessage());
    }

    private static Stream<ClientInvalidTestData> provideInvalidClientTestData() {
        return Stream.of(
                new ClientInvalidTestData(-1, "dummy client name", "Client id should be positive!"),
                new ClientInvalidTestData(1, null, "Client name should be not null and not empty!"),
                new ClientInvalidTestData(1, "", "Client name should be not null and not empty!")
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