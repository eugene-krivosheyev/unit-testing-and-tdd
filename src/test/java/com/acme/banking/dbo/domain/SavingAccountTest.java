package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Smirnov Sergey (ruasisl)
 */
class SavingAccountTest {
    private static final Integer VALID_ID = 1;

    @Test
    public void shouldCreateSavingAccountWhenParamsAreValid(){
        Client mockClient = Mockito.mock(Client.class);
        double validAmount = 0.1;


        SavingAccount sut = new SavingAccount(VALID_ID, mockClient, validAmount);

        assertAll(
                () -> assertEquals(VALID_ID, sut.getId()),
                () -> assertEquals(mockClient, sut.getClient()),
                () -> assertEquals(validAmount, sut.getAmount())
        );
    }

    @Test
    public void shouldNotCreateSavingAccountWhenIdIsNegative(){
        int invalidId = -1;
        Client mockClient = Mockito.mock(Client.class);
        double dummyAmount = 0.1;

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(invalidId, mockClient, dummyAmount));
    }

    @Test
    public void shouldNotCreateSavingAccountWhenClientIsNull(){
        Client invalidClient = null;
        double dummyAmount = 0.1;

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ID, invalidClient, dummyAmount));
    }

    @Test
    public void shouldNotCreateSavingAccountWhenAmountIsZero(){
        Client mockClient = Mockito.mock(Client.class);
        double invalidAmount = 0;

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ID, mockClient, invalidAmount));
    }
}