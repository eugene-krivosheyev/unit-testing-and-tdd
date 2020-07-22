package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProcessingITest {
    @Test
    public void shouldAccountsStateUpdatedWhenTransfer() {
        final Processing sut = new Processing();
        final SavingAccount fromAccount = new SavingAccount(null, 100.);
        final SavingAccount toAccount = new SavingAccount(null, 0.);

        sut.transfer(100., fromAccount, toAccount);

        assertEquals(0., fromAccount.getAmount(), 0.001);
        assertEquals(100., toAccount.getAmount(), 0.001);
    }
}
