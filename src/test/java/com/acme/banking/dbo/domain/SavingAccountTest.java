package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class SavingAccountTest {

    @Test
    public void shouldNotCreateAccountWhenIllegalId() {
        int illegalId = 0;
        double dummyAmount = 1.;

        Client dummyClient = new Client(1, "dummyClientName");


        IllegalArgumentException sut = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(illegalId, dummyClient, dummyAmount));
        // AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut).hasMessageContaining("id is illegal");
    }

    @ParameterizedTest
    @NullSource
    public void shouldNotCreateAccountWhenClientIsNull(Client nullClient) {
        int dummyId = 1;
        double dummyAmount = 1;


        IllegalArgumentException sut = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(dummyId, nullClient, dummyAmount));
        // AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut).hasMessageContaining("client is null");
    }

    @Test
    @Disabled
    @DisplayName("? нужно ли проверять отдельно?")
    public void shouldCanCreateAccountWhenParamsValid() {
        int dummyId = 1;
        double dummyAmount = 1;

        Client dummyClient = new Client(1, "dummyClientName");


        Assertions.assertDoesNotThrow(() -> new SavingAccount(dummyId, dummyClient, dummyAmount));
    }

    @Test
    public void shouldCreateAccountWhenParamsIsValid() {
        int accountId = 1;
        double amount = 1;
        Client client = new Client(1, "dummyClientName");


        Assertions.assertAll("SavingAccount created and store its properties",
                () -> Assertions.assertDoesNotThrow(() -> new SavingAccount(accountId, client, amount)),
                () -> {
                    SavingAccount sut = new SavingAccount(accountId, client, amount);
                    Assertions.assertEquals(accountId, sut.getId());
                    Assertions.assertEquals(amount, sut.getAmount(), .0000001);
                    Assertions.assertSame(client, sut.getClient());
                }
        );
    }
}
