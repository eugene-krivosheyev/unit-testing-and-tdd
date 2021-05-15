package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class SavingAccountTest {
    @Test
    public void shouldStoreArtifactsWhenCreated() {
        // region Given
        final int accountId = 1;
        final int accountAmount = 2;
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
}
