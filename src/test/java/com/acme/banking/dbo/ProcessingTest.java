package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessingTest {
    @Test
    @Ignore
    public void shouldAccountsStateUpdatedWhenTransfer() {
        final Processing sut = new Processing();
        final SavingAccount fromAccount = mock(SavingAccount.class);
        final SavingAccount toAccount = mock(SavingAccount.class);

        sut.transfer(100., fromAccount, toAccount);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotTransferWhenNotEnoughFunds() {
        final Processing sut = new Processing();
        final SavingAccount dummyAccount = mock(SavingAccount.class);
        final SavingAccount stubAccount = mock(SavingAccount.class);
        when(stubAccount.getAmount()).thenReturn(0.);

        sut.transfer(100., stubAccount, dummyAccount);
    }
}
