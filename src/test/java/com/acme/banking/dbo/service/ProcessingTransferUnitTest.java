package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.exception.AccountNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Processing - transfer test ")
class ProcessingTransferUnitTest {

    @InjectMocks
    Processing sut;

    @Mock
    AccountRepository accountRepository;

    @Test
    void shouldTransferAmountBetweenAccountsWhereBothAccountsExist() {

        int dummyAccountIdFrom = 1;
        int dummyAccountIdTo = 2;
        double dummyAmount = 1d;

        Account stubAccountFrom = mock(Account.class);
        Account stubAccountTo = mock(Account.class);

        when(accountRepository.findByAccountId(dummyAccountIdFrom)).thenReturn(Optional.of(stubAccountFrom));
        when(accountRepository.findByAccountId(dummyAccountIdTo)).thenReturn(Optional.of(stubAccountTo));

        sut.transfer(dummyAccountIdFrom, dummyAccountIdTo, dummyAmount);

        verify(accountRepository).findByAccountId(dummyAccountIdFrom);
        verify(accountRepository).findByAccountId(dummyAccountIdTo);
        verify(stubAccountFrom).withdraw(dummyAmount);
        verify(stubAccountTo).deposit(dummyAmount);
        verify(accountRepository).save(stubAccountFrom);
        verify(accountRepository).save(stubAccountTo);

    }

    @Test
    void shouldShowErrorMessageThatAccountIsNotFoundWhenAccountFromDoesNotExist() {

        int dummyAccountIdFrom = 1;
        int dummyAccountIdTo = 2;
        double dummyAmount = 1d;

        when(accountRepository.findByAccountId(dummyAccountIdFrom))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(AccountNotFoundException.class)
                .isThrownBy(() -> sut.transfer(dummyAccountIdFrom, dummyAccountIdTo, dummyAmount))
                .describedAs("Exception message should contain account id = 1")
                .withMessageContaining("Account with id = 1 is not found");

    }

    @Test
    void shouldShowErrorMessageWhenAccountToDoesNotExist() {

        int dummyAccountIdFrom = 1;
        int dummyAccountIdTo = 2;
        double dummyAmount = 1d;

        Account stubAccountFrom = mock(Account.class);

        when(accountRepository.findByAccountId(dummyAccountIdFrom))
                .thenReturn(Optional.of(stubAccountFrom));

        when(accountRepository.findByAccountId(dummyAccountIdTo))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(AccountNotFoundException.class)
                .isThrownBy(() -> sut.transfer(dummyAccountIdFrom, dummyAccountIdTo, dummyAmount))
                .describedAs("Exception message should contain account id = 2")
                .withMessageContaining("Account with id = 2 is not found");

    }

}