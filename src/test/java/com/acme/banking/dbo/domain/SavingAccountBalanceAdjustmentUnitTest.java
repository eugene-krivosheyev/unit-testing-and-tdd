package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.exception.NotEnoughAccountAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("Saving account - balance adjustments")
class SavingAccountBalanceAdjustmentUnitTest {


    @Test
    void shouldWithdrawAmount1FromAccountBalanceWhenBalanceIsEqual1() {

        int dummyAccountId = 1;
        double dummyAccountAmount = 1d;
        Client dummyClient = new Client(1, "dummy client name");

        SavingAccount sut = new SavingAccount(dummyAccountId, dummyClient, dummyAccountAmount);

        sut.withdraw(dummyAccountAmount);

        assertThat(sut.getAmount())
                .isEqualTo(dummyAccountAmount - 1);

    }

    @Test
    void shouldDepositAmount1ToAccountBalanceWhenAccountAmountIsEqual1() {

        int dummyAccountId = 1;
        double dummyAccountAmount = 1d;
        Client dummyClient = new Client(1, "dummy client name");

        SavingAccount sut = new SavingAccount(dummyAccountId, dummyClient, dummyAccountAmount);

        sut.deposit(dummyAccountAmount);

        assertThat(sut.getAmount())
                .isEqualTo(dummyAccountAmount + 1);

    }

    @Test
    void shouldShowErrorMessageWhenAccountAmountIsEqual1AndWithdrawAmountIs2() {

        int dummyAccountId = 1;
        double dummyAccountAmount = 1d;
        double dummyWithdrawAmount = 2d;
        Client dummyClient = new Client(1, "dummy client name");

        SavingAccount sut = new SavingAccount(dummyAccountId, dummyClient, dummyAccountAmount);

        assertThatExceptionOfType(NotEnoughAccountAmountException.class)
                .isThrownBy(() -> sut.withdraw(dummyWithdrawAmount))
                .describedAs("Error message should contain information about the account balance, id and withdraw amount")
                .withMessageContaining("Account with id 1 does not have enough money to withdraw 2.000000, current balance is 1.000000");

    }


}