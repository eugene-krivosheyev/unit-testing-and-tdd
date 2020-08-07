package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.NotEnoughFundsException;
import com.acme.banking.dbo.service.Core;
import com.acme.banking.dbo.service.TxManager;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class CoreTest {
    private Core sut;
    private TxManager txManager;

    @Before
    public void setupSut() {
        txManager = mock(TxManager.class);
        sut = new Core(txManager);
    }

    @Test
    public void shouldWithdrawAndDepositWhenTransfer() {
        double amount = 1.0;
        Account mockAccountFrom = mock(Account.class);
        Account mockAccountTo = mock(Account.class);

        sut.transfer(mockAccountFrom, mockAccountTo, amount);

        verify(mockAccountFrom).withdraw(amount);
        verify(mockAccountTo).deposit(amount);
    }


    @Test(expected = NotEnoughFundsException.class)
    public void shouldNotTransferWhenNotEnoughFunds() {
        double tooManyAmount = 1.;
        Account dummyAccount = mock(Account.class);
        Account notEnoughFundsAccount = mock(Account.class);
        doThrow(new NotEnoughFundsException())
                .when(notEnoughFundsAccount)
                    .withdraw(anyDouble());

        sut.transfer(notEnoughFundsAccount, dummyAccount, tooManyAmount);
    }

    @Test
    public void shouldWrapInTransactionWhenTransferSuccess() {
        Account dummyAccount = mock(Account.class);
        double dummyAmount = 0.;

        sut.transfer(dummyAccount, dummyAccount, dummyAmount);

        verify(txManager).start();
        verify(txManager).commit();
    }

    @Test
    public void shouldNotCommitTransactionWhenTransferError() {
        double dummyAmount = 0.;
        Account dummyAccount = mock(Account.class);
        Account errorStubAccount = mock(Account.class);
        doThrow(new NotEnoughFundsException())
                .when(errorStubAccount)
                    .withdraw(anyDouble());

        sut.transfer(errorStubAccount, dummyAccount, dummyAmount);

        verify(txManager).start();
        verify(txManager, times(0)).commit();
        verify(txManager).rollback();
    }
}
