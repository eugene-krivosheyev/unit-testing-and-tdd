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
        int id = 1;
        Client client = new Client(id, "testName");
        double amount = 0.1;

        //endregion

        //region when
        SavingAccount sut = new SavingAccount(id, client, amount);
        //endregion

        //region then
        assertEquals(id, sut.getId());
        assertEquals(client, sut.getClient());
        assertEquals(amount, sut.getAmount());
        //endregion

    }
    @Test

    public void shouldNotCreateSavingAccountWhenIdIsNegative(){
        //region given
        int id = -1;
        Client client = new Client(1, "testName");
        double amount = 0.1;

        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion

        //region then
        //endregion
    }
    @Test
    public void shouldNotCreateSavingAccountWhenClientIsNull(){
        //region given
        int id = 1;
        Client client = null;
        double amount = 0.1;

        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion

        //region then
        //endregion
    }
    @Test
    public void shouldNotCreateSavingAccountWhenAmountIsZero(){
        //region given
        int id = 1;
        Client client = new Client(id, "testName");
        double amount = 0;

        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
        //endregion

        //region then
        //endregion
    }
}