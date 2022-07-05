package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Smirnov Sergey (ruasisl)
 */
class SavingAccountTest {
    @Test
    public void shouldCreateSavingAccountWhenParamsAreValid(){
        //region given
        int validId = 1;
        Client validClient = new Client(validId, "dummyName");
        double validAmount = 0.1;

        //endregion

        //region when
        SavingAccount sut = new SavingAccount(validId, validClient, validAmount);
        //endregion

        //region then
        assertAll(
                () -> assertEquals(validId, sut.getId()),
                () -> assertEquals(validClient, sut.getClient()),
                () -> assertEquals(validAmount, sut.getAmount()),
                () -> assertTrue(validClient.getAccounts().contains(sut)),
                () -> assertEquals(1, validClient.getAccounts().size())
        );

        //endregion
    }

    @Test
    public void shouldNotCreateSavingAccountWhenIdIsNegative(){
        //region given
        int invalidId = -1;
        Client dummyClient = new Client(1, "testName");
        double dummyAmount = 0.1;

        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(invalidId, dummyClient, dummyAmount));
        //endregion

        //region then
        //endregion
    }
    @Test
    public void shouldNotCreateSavingAccountWhenClientIsNull(){
        //region given
        int validId = 1;
        Client invalidClient = null;
        double dummyAmount = 0.1;

        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(validId, invalidClient, dummyAmount));
        //endregion

        //region then
        //endregion
    }
    @Test
    public void shouldNotCreateSavingAccountWhenAmountIsZero(){
        //region given
        int validId = 1;
        Client validClient = new Client(validId, "testName");
        double invalidAmount = 0;

        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(validId, validClient, invalidAmount));
        //endregion

        //region then
        //endregion
    }
}