package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Saving Account Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SavingAccountTest {

    @ParameterizedTest
    @MethodSource("getSavingAccountsInputParams")
    public void validateSavingAccountsInputParameters(SavingAccountsInputParams savingAccountsInputParams) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(savingAccountsInputParams.getId(), savingAccountsInputParams.getClient(), savingAccountsInputParams.getAmount()));
        assertEquals(thrown.getMessage(), savingAccountsInputParams.getExpectedExceptionMessage(), savingAccountsInputParams.getExceptionMessage());
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        Client client = new Client(1, "Test Client");
        SavingAccount savingAccount = new SavingAccount(1, client, 1);
        Assertions.assertThat(savingAccount)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("amount", 1.0)
                .hasFieldOrPropertyWithValue("client", client);
    }

    private Stream<SavingAccountsInputParams> getSavingAccountsInputParams() {
        Client client = new Client(1, "Test Client");
        return Stream.of(
                new SavingAccountsInputParams(-1, 1, client, "id < 0", "shouldNotCreateSavingAccountWhenIdIsNegative"),
                new SavingAccountsInputParams(1, -1.0, client, "amount < 0", "shouldNotCreateSavingAccountWhenAmmountIsNegative"),
                new SavingAccountsInputParams(1, 1.0, null, "client is null!", "shouldNotCreateSavingAccountWhenClientIsNull")
        );
    }

}

class SavingAccountsInputParams {
    private int id;
    private double amount;
    Client client;
    private String expectedExceptionMessage;
    private String exceptionMessage;

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Client getClient() {
        return client;
    }

    public String getExpectedExceptionMessage() {
        return expectedExceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public SavingAccountsInputParams(int id, double amount, Client client, String expectedExceptionMessage, String exceptionMessage) {
        this.id = id;
        this.amount = amount;
        this.client = client;
        this.expectedExceptionMessage = expectedExceptionMessage;
        this.exceptionMessage = exceptionMessage;
    }
}
