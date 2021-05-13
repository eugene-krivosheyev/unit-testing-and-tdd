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
        //region given
        final int accountId = 1;
        final Client accountClient = new Client(1, "dummy client name");
        final double accountAmount = 1;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accountId, accountClient, accountAmount);
        assumeTrue(sut != null);
        //endregion

        //region then
        assertAll("SavingAccount store its properties",
                () -> assertEquals(accountId, sut.getId()),
                () -> assertSame(accountClient, sut.getClient()),
                () -> assertEquals(accountAmount, sut.getAmount())
        );
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNegative() {
        //region given
        final int accountId = -1;
        final Client accountClient = new Client(1, "dummy client name");
        final double accountAmount = 1;
        //endregion

        //region when
        Executable sut = () -> new SavingAccount(accountId, accountClient, accountAmount);
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, sut);
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //region given
        final int accountId = 1;
        final Client accountClient = null;
        final double accountAmount = 1;
        //endregion

        //region when
        Executable sut = () -> new SavingAccount(accountId, accountClient, accountAmount);
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, sut);
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountIsNegative() {
        //region given
        final int accountId = 1;
        final Client accountClient = new Client(1, "dummy client name");;
        final double accountAmount = -1;
        //endregion

        //region when
        Executable sut = () -> new SavingAccount(accountId, accountClient, accountAmount);
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, sut);
        //endregion
    }
}
