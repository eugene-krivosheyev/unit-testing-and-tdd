package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    private static final int CORRECT_ID =1;
    private static final int INVALID_ID = -1;
    private static final int ZERO_ID = 0;
    private static final double DUMMY_AMOUNT = 12;
    private static final double ZERO_AMOUNT = 0;
    private static final double NEGATIVE_AMOUNT = -12;
    private static final int DUMMY_ID =1;
    private static final String DUMMY_NAME = "Dymmy name";
    private static final Client DUMMY_CLIENT = new Client(DUMMY_ID,DUMMY_NAME);



    @Test
    void shouldSaveAccount() {
        SavingAccount sut = new SavingAccount(CORRECT_ID, DUMMY_CLIENT, DUMMY_AMOUNT);
        assertNotNull(sut);
    }

    @Test
    void shouldntSaveAccountWithZeroId() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ZERO_ID, DUMMY_CLIENT, DUMMY_ID));

    }

    @Test
    void shouldntSaveAccountWithNegativeId() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(INVALID_ID, DUMMY_CLIENT, DUMMY_AMOUNT));
    }

    @Test
    void shouldntSaveAccountWithNullClient() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(DUMMY_ID, null, DUMMY_AMOUNT));
    }

    @Test
    void shouldntSaveAccountWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(DUMMY_ID, DUMMY_CLIENT, NEGATIVE_AMOUNT));
    }

    @Test
    void shouldntSaveAccountWithZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(DUMMY_ID, DUMMY_CLIENT, ZERO_AMOUNT));
    }

    @Test
    void shouldnAddAccountForClient() {
        SavingAccount sut = new SavingAccount(CORRECT_ID, DUMMY_CLIENT, DUMMY_AMOUNT);

        sut.addAccountForClient();

        assertThat(sut.getClient().getAccounts())
                .isNotEmpty()
                .contains(sut);
    }
}
