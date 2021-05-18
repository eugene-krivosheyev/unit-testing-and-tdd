package com.acme.banking.dbo;


import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("Test suite")
public class SavingAccountTest {
    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int accountId = 1;
        final double amount = 5;
        final Client dummy = new Client(1, "AA");
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accountId, dummy, amount);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(accountId, sut.getId()),
                () -> assertEquals(amount, sut.getAmount()),
                () -> assertEquals(dummy.getName(), sut.getClient().getName()),
                () -> assertEquals(dummy.getId(), sut.getClient().getId())
        );

        //endregion
    }
    @Test
    public void shouldReturnErrorWhenIdNegative() {
        //region given
        final int accountId = -1;
        final double amount = 20;
        final Client dummy = new Client(1, "AA");
        //endregion

        //region when
        try {
            SavingAccount sut = new SavingAccount(accountId, dummy, amount);
        }
        //endregion

        //region then
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Id is negative");
        }
    }
    @Test
    public void shouldReturnErrorWhenNameEmpty() {
        //region given
        final int accountId = 1;
        final double amount = -5;
        final Client dummy = new Client(1, "AA");
        //endregion

        //region when
        try {
            SavingAccount sut = new SavingAccount(accountId, dummy, amount);
        }
        //endregion

        //region then
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Amount is negative");
        }
    }
}
