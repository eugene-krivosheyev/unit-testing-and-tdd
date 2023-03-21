package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static com.acme.banking.dbo.TestData.CLIENT_SUT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccountTransactionTest {

    @Test
    void shouldWithDrawAmountWhenAmountIsEnough() {
        double startAmount = 100.0;
        SavingAccount sut = new SavingAccount(1, CLIENT_SUT, startAmount);

        Assumptions.assumeTrue(sut.getAmount() == startAmount);

        sut.withdraw(100.0);

        assertThat(sut)
                .hasFieldOrPropertyWithValue("amount", 0.0);
    }

    @Test
    void shouldDepositAmountWhenAmountIsPositive() {
        double startAmount = 0;
        SavingAccount sut = new SavingAccount(1, CLIENT_SUT, startAmount);

        Assumptions.assumeTrue(sut.getAmount() == startAmount);

        sut.deposit(100.0);

        assertThat(sut)
                .hasFieldOrPropertyWithValue("amount", 100.0);
    }

}