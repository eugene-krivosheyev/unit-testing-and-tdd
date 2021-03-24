package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test suite")
public class ProcessingTest {

    private AccountRepository accountRepository = mock(AccountRepository.class);

    private Processing processing = new Processing(accountRepository);

    @Test
    public void shouldTransferAmountFromToWhenDataCorrect() {
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);

        when(accountRepository.findById(1)).thenReturn(account1);
        when(accountRepository.findById(2)).thenReturn(account2);

        when(account1.getAmount()).thenReturn(200.0);
        when(account2.getAmount()).thenReturn(100.0);

        processing.transfer(1, 2, 100.0);

        assertAll(
            () -> verify(accountRepository).findById(1),
            () -> verify(accountRepository).findById(2),
            () -> verify(account1).setAmount(100),
            () -> verify(account2).setAmount(200),
            () -> verify(accountRepository, times(1)).save(account1),
            () -> verify(accountRepository, times(1)).save(account2)
        );
    }

    @Test
    public void shouldBeErrorWhenAmountIsNegative() {
        int dummyAccountId1 = 1;
        int dummyAccountId2 = 2;

        assertThrows(IllegalArgumentException.class, () -> processing.transfer(dummyAccountId1, dummyAccountId2, -1));
    }
}
