package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class ProcessingTest {

    @Test
    public void shouldWithdrawAmountFromAccountWhenCash() {
        Account accountDoubler = mock(SavingAccount.class);
        AccountRepository accountRepositoryDoubler = mock(AccountRepository.class);
        Processing sut = new Processing(accountRepositoryDoubler);
        UUID accountId = UUID.randomUUID();
        double amount = 34.37;

        when(accountRepositoryDoubler.findById(accountId)).thenReturn(accountDoubler);

        sut.cash(amount, accountId);

        verify(accountDoubler, times(1)).withdraw(amount);
        verify(accountRepositoryDoubler, times(1)).findById(accountId);
        verify(accountRepositoryDoubler, times(1)).save(accountDoubler);
    }
}
