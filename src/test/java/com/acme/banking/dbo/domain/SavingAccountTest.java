package com.acme.banking.dbo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SavingAccountTest {

    private final Client client = new Client(10, "name");

    @Test
    void constructor_whenArgsValid_thenCreateNewSavingAccount() {
        assertDoesNotThrow(() -> new SavingAccount(10, client, 10.0));
    }

    @Test
    void constructor_when_thenCreateNewSavingAccount() {


    }

}