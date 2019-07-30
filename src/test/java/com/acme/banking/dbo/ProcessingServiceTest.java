package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.ProcessingService;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingServiceTest {
    @Test
    public void shouldCallAccountsTransferMethodsWhenTransfer() {
        Cash mockCash = mock(Cash.class);
        AccountRepository stubRepository = mock(AccountRepository.class);
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);
        when(stubRepository.findAccountById(any(UUID.class)))
                .thenReturn(account1)
                .thenReturn(account2);
        final ProcessingService sut = new ProcessingService(stubRepository, mockCash);

        final UUID fromAccountId = UUID.randomUUID();
        final UUID toAccountId = UUID.randomUUID();
        sut.transfer(1., fromAccountId, toAccountId);

        verify(stubRepository).findAccountById(fromAccountId);
        verify(stubRepository).findAccountById(toAccountId);
        verify(account1).withdraw(1.);
        verify(account2).credit(1.);
        verify(stubRepository).save(account1);
        verify(stubRepository).save(account2);
    }
}
