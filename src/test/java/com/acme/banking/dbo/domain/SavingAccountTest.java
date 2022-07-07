package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Smirnov Sergey (ruasisl)
 */
class SavingAccountTest {
    @Test
    public void shouldCreateSavingAccountWhenParamsAreValid(){
        int validId = 1;
        Client mockClient = Mockito.mock(Client.class);
        double validAmount = 0.1;


        SavingAccount sut = new SavingAccount(validId, mockClient, validAmount);

        assertAll(
                () -> assertEquals(validId, sut.getId()),
                () -> assertEquals(mockClient, sut.getClient()),
                () -> assertEquals(validAmount, sut.getAmount())
        );
    }

    @Test
    public void shouldNotCreateSavingAccountWhenIdIsNegative(){
        //region given
        int invalidId = -1;
        Client mockClient = Mockito.mock(Client.class);
        double dummyAmount = 0.1;

        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(invalidId, mockClient, dummyAmount));
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
        Client mockClient = Mockito.mock(Client.class);
        double invalidAmount = 0;

        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(validId, mockClient, invalidAmount));
        //endregion

        //region then
        //endregion
    }
}