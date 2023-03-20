package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static com.acme.banking.dbo.TestData.CLIENT_SUT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
    void shouldWithdrawThrowErrorWhenAmountIsNotEnough() {
        SavingAccount sut = new SavingAccount(1, CLIENT_SUT, 0);

        assertThatThrownBy(() -> sut.withdraw(1.0))
                .hasMessage("Amount not enough");
    }

    @Test
    void shouldWithdrawThrowErrorWhenAmountIsNegative() {
        SavingAccount sut = new SavingAccount(1, CLIENT_SUT, 0);

        assertThatThrownBy(() -> sut.withdraw(0))
                .hasMessage("Amount must be more 0");
    }

    @Test
    void shouldDepositThrowErrorWhenAmountIsNegative() {
        SavingAccount sut = new SavingAccount(1, CLIENT_SUT, 0);

        assertThatThrownBy(() -> sut.deposit(0))
                .hasMessage("Amount must be more 0");
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