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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("SavingAccount class tests")
public class SavingAccountTest {
    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int accountId = 1;
        final Client accountClient = new Client(1, "dummy client name");
        final double accountAmount = 1;

        SavingAccount sut = new SavingAccount(accountId, accountClient, accountAmount);
        assumeTrue(sut != null);

        assertAll("SavingAccount store its properties",
                () -> assertEquals(accountId, sut.getId()),
                () -> assertSame(accountClient, sut.getClient()),
                () -> assertEquals(accountAmount, sut.getAmount())
        );
    }

    @Test
    public void shouldClientContainsAccountWhenAccountForClientWasCreated() {
        final Client dummyClient = new Client(1, "dummy");

        final Account sut = new SavingAccount(1, dummyClient, 1.0);

        assertTrue(dummyClient.getAccounts().contains(sut));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAccountTestData")
    public void shouldThrowIllegalArgumentExceptionWhenFieldIsInvalid(AccoountInvalidTestData invalidData) {
        Executable sut = () -> new SavingAccount(invalidData.getId(), invalidData.getClient(), invalidData.getAmount());

        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                sut,
                invalidData.getErrorMessage());
    }

    private static Stream<AccoountInvalidTestData> provideInvalidAccountTestData() {
        return Stream.of(
                new AccoountInvalidTestData(-1, new Client(1, "dummy client name"), 1, "Account id should be positive!"),
                new AccoountInvalidTestData(1, null, 1, "Account client should be not null!"),
                new AccoountInvalidTestData(1, new Client(1, "dummy client name"), -1, "Account amount should be positive!")
        );
    }
}

class AccoountInvalidTestData {
    private int id;
    private Client client;
    private double amount;
    private String errorMessage;

    AccoountInvalidTestData(int id, Client client, double amount, String errorMessage) {
        this.id = id;
        this.client = client;
        this.amount = amount;
        this.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
