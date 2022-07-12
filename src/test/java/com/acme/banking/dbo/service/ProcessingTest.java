package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.domain.SavingAccount;
import com.acme.banking.dbo.domain.service.Processing;
import com.acme.banking.dbo.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProcessingTest {

    private static final int STUB_ACCOUNT_ID = 1;
    private static final double STUB_AMOUNT = 42D;

    @Test
    void shouldWithdrawCashFromAccount() {

        AccountRepository repository = mock(AccountRepository.class);
        SavingAccount account = mock(SavingAccount.class);

        Processing sut = new Processing(repository);
        when(account.getAmount()).thenReturn(STUB_AMOUNT);
        when(repository.getAccountById(STUB_ACCOUNT_ID)).thenReturn(account);

        sut.cash(STUB_AMOUNT, STUB_ACCOUNT_ID);

        Assertions.assertThat(repository.getAccountById(STUB_ACCOUNT_ID).getAmount()).isZero();
    }
}
