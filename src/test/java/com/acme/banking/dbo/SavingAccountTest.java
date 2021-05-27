package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.*;
import static org.assertj.core.api.ThrowableAssert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("SavingAccount class tests")
public class SavingAccountTest {
    @Test
    @Disabled
    public void shouldStorePropertiesWhenCreated() {
        final int accountId = 1;
        final Client accountClient = new Client(1, "dummy client name");
        final double accountAmount = 1;

        SavingAccount sut = new SavingAccount(accountId, accountClient, accountAmount);
        assumeThat(sut).isNotNull();

        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", accountId)
                .hasFieldOrPropertyWithValue("client", accountClient)
                .hasFieldOrPropertyWithValue("amount", accountAmount);
    }

    @Test
    @Disabled
    public void shouldClientContainsAccountWhenAccountForClientWasCreated() {
        final Client dummyClient = new Client(1, "dummy");

        final Account sut = new SavingAccount(1, dummyClient, 1.0);

        assertThat(dummyClient.getAccounts().contains(sut)).isTrue();
    }

    @Test
    public void shouldClientContainsAccountWhenAccountForClientWasCreatedUnit() {
        final Client dummyClient = mock(Client.class);
        final Collection<Account> dummyAccounts = new ArrayList<>();
        final Account sut = new SavingAccount(1, dummyClient, 1.0);

        dummyAccounts.add(sut);
        when(dummyClient.getAccounts()).thenReturn(dummyAccounts);

        assertTrue(dummyClient.getAccounts().contains(sut));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAccountUnitTestData")
    public void shouldThrowIllegalArgumentExceptionWhenFieldIsInvalid(AccountInvalidTestData invalidData) {
        ThrowingCallable sut = () -> new SavingAccount(invalidData.getId(), invalidData.getClient(), invalidData.getAmount());

        assertThatIllegalArgumentException().isThrownBy(sut)
                .withMessageMatching(invalidData.getErrorMessage());
    }

    private static Stream<AccountInvalidTestData> provideInvalidAccountTestData() {
        return Stream.of(
                new AccountInvalidTestData(-1, new Client(1, "dummy client name"), 1, "Account id should be positive!"),
                new AccountInvalidTestData(1, null, 1, "Account client should be not null!"),
                new AccountInvalidTestData(1, new Client(1, "dummy client name"), -1, "Account amount should be positive!")
        );
    }

    private static Stream<AccountInvalidTestData> provideInvalidAccountUnitTestData() {
        return Stream.of(
                new AccountInvalidTestData(-1, mock(Client.class), 1, "Account id should be positive!"),
                new AccountInvalidTestData(1, null, 1, "Account client should be not null!"),
                new AccountInvalidTestData(1, mock(Client.class), -1, "Account amount should be positive!")
        );
    }
}

class AccountInvalidTestData {
    private int id;
    private Client client;
    private double amount;
    private String errorMessage;

    AccountInvalidTestData(int id, Client client, double amount, String errorMessage) {
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
