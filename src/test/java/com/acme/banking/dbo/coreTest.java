package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Core;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class coreTest {
    @Test
    public void shouldWithDrawAndDepositWhenTransfer() {
        double amount = 1.0;
        Account mockAccountFrom = mock(Account.class);
        Account mockAccountTo = mock(Account.class);

        Core sut = new Core();
        sut.transfer(mockAccountFrom,mockAccountTo,amount);

        verify(mockAccountFrom).withdraw(amount);
        verify(mockAccountTo).deposit(amount);

    }

    /*
    @Test(expected = NotEnoughFundsException.class)
    public void shouldNotTransferWhenNotEnoughFunds() {
        Core sut = new Core();
        sut.transfer();
    }
    */


}
