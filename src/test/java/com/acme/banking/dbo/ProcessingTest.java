package com.acme.banking.dbo;

import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessingTest {
    @Test
    public void shouldGetStoredAccountWhenGetExistedAccountById() {
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        Account accountStub = mock(Account.class);
        when(accountRepoStub.getAccountsByClientId(1)).thenReturn(Arrays.asList(accountStub));
        final Processing sut = new Processing(accountRepoStub);

        assertThat(sut.getAccountsByClientId(1)).containsExactly(accountStub);
    }

    @Test
    public void shouldGetErrorAccountWhenGetNotExistedAccountById() {
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        when(accountRepoStub.getAccountsByClientId(2)).thenThrow(new IllegalStateException("!!!!!"));
        final Processing sut = new Processing(accountRepoStub);

        assertThrows(
                IllegalStateException.class,
                () -> sut.getAccountsByClientId(2)
        );
    }
}
