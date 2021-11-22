package com.acme.banking.dbo;

import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    public void shouldGetStoredAccountWhenGetExistedAccountById() {
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        Account accountStub = mock(Account.class);

        when(accountRepoStub.getAccountsByClientId(any(Integer.class)))
                .thenReturn(Arrays.asList(accountStub))
                .thenReturn(Collections.EMPTY_SET)
                .thenThrow(new IllegalStateException("NO ENTITY!"));

        final Processing sut = new Processing(accountRepoStub);

        assertThat(sut.getAccountsByClientId(1)).containsExactly(accountStub);
    }

    @Test
    public void shouldGetErrorAccountWhenGetNotExistedAccountById() {
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        when(accountRepoStub.getAccountsByClientId(anyInt())).thenThrow(new IllegalStateException("!!!!!"));
        final Processing sut = new Processing(accountRepoStub);

        assertThrows(
                IllegalStateException.class,
                () -> sut.getAccountsByClientId(2)
        );
    }

    @Test
    public void shouldTransferWhenValidAmount() {
        AccountRepository accountsRepoMock = mock(AccountRepository.class);
        Account accountFromSpy = spy(new SavingAccount(1, null, 0));
        Account accountToStub = mock(Account.class);
        when(accountFromSpy.getAmount()).thenReturn(10.);
        when(accountToStub.getAmount()).thenReturn(2.);
        when(accountsRepoMock.getAccountById(1)).thenReturn(accountFromSpy);
        when(accountsRepoMock.getAccountById(2)).thenReturn(accountToStub);
        Processing sut = new Processing(accountsRepoMock);

        sut.transfer(1, 2, 9);

        verify(accountsRepoMock, times(1)).getAccountById(1);
        verify(accountsRepoMock, atLeastOnce()).getAccountById(2); //anyInt()
        verify(accountFromSpy).setAmount(1);
        verify(accountToStub).setAmount(11);
        verify(accountsRepoMock).save(accountFromSpy);
        verify(accountsRepoMock).save(accountToStub);
    }
}
