package com.acme.banking.dbo;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

public class ProcessingTest {

    @Test
    public void shouldChangeAmountWhenTransfer() {

        // given
        final double DUMMY_AMOUNT = 123.;
        Account accountFromStub = mock(Account.class);
        Account accountToStub = mock(Account.class);
        when(accountFromStub.getId()).thenReturn(1);
        when(accountToStub.getId()).thenReturn(2);

        AccountRepository accountRepositoryStub = mock(AccountRepository.class);
        when(accountRepositoryStub.findById(1)).thenReturn(accountFromStub);
        when(accountRepositoryStub.findById(2)).thenReturn(accountToStub);

        Processing sut = new Processing(accountRepositoryStub);

        //when
        sut.transfer(accountFromStub.getId(), accountToStub.getId(), DUMMY_AMOUNT);

        // then
        assertAll(
                () -> verify(accountFromStub).withdraw(DUMMY_AMOUNT),
                () -> verify(accountToStub).deposit(DUMMY_AMOUNT),
                () -> verify(accountRepositoryStub, times(1)).save(accountFromStub),
                () -> verify(accountRepositoryStub, times(1)).save(accountToStub)
        );
    }

    @Test
    public void GetErrorWhenTransferZeroAmount() {

        // given
        final int DUMMY_FROM_ACCOUNT = 1;
        final int DUMMY_TO_ACCOUNT = 2;
        AccountRepository accountRepositoryStub = mock(AccountRepository.class);
        Processing sut = new Processing(accountRepositoryStub);

        //when
        ;

        Assertions.assertThrows(IllegalArgumentException.class,
                () ->sut.transfer(DUMMY_FROM_ACCOUNT, DUMMY_TO_ACCOUNT, 0));
    }

    @Test
    public void GetErrorWhenSameAccount() {

        // given
        final int DUMMY_ACCOUNT_ID = 1;
        final double DUMMY_AMOUNT = 123.;
        AccountRepository accountRepositoryStub = mock(AccountRepository.class);
        Processing sut = new Processing(accountRepositoryStub);

        //when
        ;

        Assertions.assertThrows(IllegalArgumentException.class,
                () ->sut.transfer(DUMMY_ACCOUNT_ID, DUMMY_ACCOUNT_ID, DUMMY_AMOUNT));
    }
}
