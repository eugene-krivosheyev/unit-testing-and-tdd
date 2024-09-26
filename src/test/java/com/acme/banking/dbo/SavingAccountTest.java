package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import static com.acme.banking.dbo.domain.Errors.ACCOUNT_NEGATIVE_AMOUNT_MESSAGE;
import static com.acme.banking.dbo.domain.Errors.ACCOUNT_NEGATIVE_ID_MESSAGE;
import static com.acme.banking.dbo.domain.Errors.ACCOUNT_NULL_CLIENT_MESSAGE;
import com.acme.banking.dbo.domain.SavingAccount;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("SavingAccount Test")
public class SavingAccountTest {

    @Test
    @DisplayName("Negative accountId testCase")
    public void shouldThrowExceptionWhenAccountIdNegative() {
        final var accountId = -1;
        final var client = new Client(1, "dummyClientName");
        final var amount = 1;

        var negativeAccountIdEx = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(accountId, client, amount));
        assertEquals(ACCOUNT_NEGATIVE_ID_MESSAGE, negativeAccountIdEx.getMessage());
    }

    @Test
    @DisplayName("Null client testCase")
    public void shouldThrowExceptionWhenClientIsNull() {
        final var accountId = 1;
        final Client client = null;
        final var amount = 1;

        var nullClientEx = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(accountId, client, amount));
        assertEquals(ACCOUNT_NULL_CLIENT_MESSAGE, nullClientEx.getMessage());
    }

    @Test
    @DisplayName("Negative amount testCase")
    public void shouldThrowExceptionWhenAmountIsNegative() {
        final var accountId = 1;
        final Client client = new Client(1, "dummyClientName");
        final var amount = -1;

        var negativeAmountEx = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(accountId, client, amount));
        assertEquals(ACCOUNT_NEGATIVE_AMOUNT_MESSAGE, negativeAmountEx.getMessage());
    }

    @Test
    @DisplayName("Saving account initialize with zero params testCase")
    public void shouldCreateSavingAccountWhenAccountAndAmountAreZero() {
        final var accountId = 0;
        final var client = new Client(1, "dummyClientName");
        final var amount = 0;

        SavingAccount sut = new SavingAccount(accountId, client, amount);
        assumeTrue(sut != null);

        assertAll("Saving account stores its properties",
                () -> assertEquals(accountId, sut.getId()),
                () -> assertEquals(client, sut.getClient()),
                () -> assertEquals(amount, sut.getAmount())
        );
    }

    @Test
    @DisplayName("Saving account initialize with valid params testCase")
    public void shouldCreateSavingAccountWhenParamsAreValid() {
        final var accountId = 1;
        final var client = new Client(1, "dummyClientName");
        final var amount = 1;

        SavingAccount sut = new SavingAccount(accountId, client, amount);
        assumeTrue(sut != null);

        assertAll("Saving account stores its properties",
                () -> assertEquals(accountId, sut.getId()),
                () -> assertEquals(client, sut.getClient()),
                () -> assertEquals(amount, sut.getAmount())
        );
    }
}
