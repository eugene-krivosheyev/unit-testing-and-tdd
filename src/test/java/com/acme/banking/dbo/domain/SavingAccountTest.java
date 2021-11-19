package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    @Test
    void shouldBeCreatedAndClientMustHaveIt() {
        int id = 1;
        double amount = 1;
        Client dummy = new Client(id, "Dummy");

        SavingAccount sut = new SavingAccount(id, dummy, amount);

        assertAll(
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(amount, sut.getAmount()),
                () -> assertSame(dummy, sut.getClient()),
                () -> assertTrue(dummy.getAccounts().contains(sut))
        );
    }

    @Test
    void shouldNotBeCreatedDueToError() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, null, -1)),
                () -> assertThrows(NullPointerException.class, () -> new SavingAccount(1, null, 1))
        );
    }
}