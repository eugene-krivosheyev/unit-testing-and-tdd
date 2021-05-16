package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class SavingAccountTest {
    @Test
    public void shouldStoreArtifactsWhenCreated() {
        // region Given
        final int accountId = 1;
        final double accountAmount = 2;
        final Client dummy = new Client(3, "TestClient");
        // endregion

        // region When
        SavingAccount sut = new SavingAccount(1, dummy, 2);
        assumeTrue(sut != null);
        // endregion

        // region Then
        assertEquals(accountId, sut.getId());
        assertEquals(accountAmount, sut.getAmount());
        assertEquals(dummy, sut.getClient());
        // endregion
    }

    @Test
    public void shouldNotCreatedWhenNegativeAccountId() {
        final int negativeAccountId = -1;
        final double dummyAccountAmount = 1;
        final Client dummy = new Client (1, "dummy name");

        final IllegalArgumentException thrown = assertThrows
                (IllegalArgumentException.class,
                        () -> new SavingAccount(negativeAccountId, dummy, dummyAccountAmount),
                        "id < 0: " + negativeAccountId);
    }

    @Test
    public void shouldNotCreatedWhenNegativeAccountAmount() {
        final int dummyAccountId = 1;
        final double negativeAccountAmount = -1;
        final Client dummy = new Client (1, "dummy name");

        //      example with correct 2nd check
        final IllegalArgumentException thrown1 = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyAccountId, dummy, negativeAccountAmount));
        assertEquals("amount < 0: " + negativeAccountAmount, thrown1.getMessage());

        final IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyAccountId, dummy, negativeAccountAmount),
                "amount << 0: " + negativeAccountAmount);
    }

    @Test
    public void shouldNotCreatedWhenNegativeNullClient() {
        final int dummyAccountId = 1;
        final double dummyAccountAmount = 1;
        final Client nullName = null;

        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyAccountId, nullName, dummyAccountAmount),
                "client is null");
    }
}
