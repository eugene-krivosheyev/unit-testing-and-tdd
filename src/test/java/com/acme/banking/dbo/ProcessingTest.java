package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private Processing sut;

    @Before // static @BeforeClass
    public void setUp() {
        sut = new Processing();
    }

    @After // static @AfterClass
    public void dearDown() {

    }

    @Test
    public void shouldAccountsStateUpdatedWhenTransfer() {
        final SavingAccount mockToAccount = mock(SavingAccount.class);
        final SavingAccount mockFromAccount = mock(SavingAccount.class);
        when(mockFromAccount.getAmount()).thenReturn(200.);

        sut.transfer(100., mockFromAccount, mockToAccount);

        verify(mockFromAccount, times(1)).withdraw(100.);
        verify(mockToAccount).deposit(anyDouble());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotTransferWhenNotEnoughFunds() {
        final Processing sut = new Processing();
        final SavingAccount dummyAccount = mock(SavingAccount.class);
        final SavingAccount stubAccount = mock(SavingAccount.class);
        when(stubAccount.getAmount()).thenReturn(0.);

        sut.transfer(100., stubAccount, dummyAccount);
    }

    @Test
    public void shouldAccountsStateUpdatedWhenAccountsExistsInDb() {
        AccountRepository accounts = new AccRepoDBBuilder()
                .withAccount(0, 200.)
                .withAccount(1)
            .build();
        final Processing sut = new Processing(accounts);

        sut.transfer(100., 0, 1);

        verify(mockFromAccount).withdraw(100.);
        verify(mockToAccount).deposit(100.);
    }
}
