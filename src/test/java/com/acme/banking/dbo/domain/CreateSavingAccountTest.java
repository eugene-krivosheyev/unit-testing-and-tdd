package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static com.acme.banking.dbo.TestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CreateSavingAccountTest {

    @Test
    public void shouldNotCreateNewAccountWhenIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, CLIENT_SUT, VALID_AMOUNT_1));
    }

    @Test
    public void shouldNotCreateNewAccountWhenAmountNegative() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ID_1, CLIENT_SUT, -1.0));
    }

    @Test
    public void shouldNotCreateNewAccountWhenClientNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ID_0, null, VALID_AMOUNT_0));
    }

    @Test
    public void shouldCreateNewAccountSuccessfullyWhenArgumentsValid() {
        SavingAccount sut1 = SAVING_ACCOUNT_SUT;
        SavingAccount sut2 = new SavingAccount(VALID_ID_0, CLIENT_SUT, VALID_AMOUNT_0);

        assumeTrue(sut1 != null);
        assumeTrue(sut2 != null);

        assertAll(
                () -> assertEquals(VALID_ID_1, sut1.getId()),
                () -> assertEquals(VALID_AMOUNT_1, sut1.getAmount()),
                () -> assertEquals(CLIENT_SUT, sut1.getClient()),
                () -> assertEquals(VALID_ID_0, sut2.getId()),
                () -> assertEquals(VALID_AMOUNT_0, sut2.getAmount())
        );
    }

}