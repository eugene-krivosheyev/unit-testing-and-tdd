package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

class SavingAccountTest {
    @Test
    void shouldCreateSavingAccount() {
        SavingAccount savingAccount = new SavingAccount(100, new Client(200, "Ivan"), 100.24);
    }

}