package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNegative() {
        final int accountId = -1;
        final Client accountClient = new Client(1, "dummy client name");
        final double accountAmount = 1;

        Executable sut = () -> new SavingAccount(accountId, accountClient, accountAmount);

        assertThrows(
                IllegalArgumentException.class,
                sut,
                "Account id should be positive!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        final int accountId = 1;
        final Client accountClient = null;
        final double accountAmount = 1;

        Executable sut = () -> new SavingAccount(accountId, accountClient, accountAmount);

        assertThrows(
                IllegalArgumentException.class,
                sut,
                "Account client should be not null!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountIsNegative() {
        final int accountId = 1;
        final Client accountClient = new Client(1, "dummy client name");;
        final double accountAmount = -1;

        Executable sut = () -> new SavingAccount(accountId, accountClient, accountAmount);

        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                sut,
                "Account amount should be positive!");
    }
}
