package com.acme.banking.dbo;

import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;

public class ProcessingTest {

    {
        ClientRepository clientRepoStub = mock(ClientRepository.class);
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
    }

    @Test
    void shouldAddToRepositoryWhenCreateClient () {
        ClientRepository clientRepoStub = mock(ClientRepository.class);
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(accountRepoStub, clientRepoStub, cashStub);

        sut.createClient("aaa");

        verify(clientRepoStub).add("aaa");
    }

    @Test
    void shouldGetAccountsWhenClientExisted () {
        ClientRepository clientRepoStub = mock(ClientRepository.class);
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(accountRepoStub, clientRepoStub, cashStub);

        sut.getAccountsByClientId(1);

        verify(accountRepoStub).getAccountsByClientId(1);
    }

    @Test
    void shouldTransferWhenAccountsAndAmountValid () {
        ClientRepository clientRepoStub = mock(ClientRepository.class);
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(accountRepoStub, clientRepoStub, cashStub);
        Account accountFromStub = mock(Account.class);
        Account accountToStub = mock(Account.class);
        when(accountFromStub.getAmount()).thenReturn(10.);
        when(accountToStub.getAmount()).thenReturn(20.);
        when(accountRepoStub.getAccountById(1)).thenReturn(accountFromStub);
        when(accountRepoStub.getAccountById(2)).thenReturn(accountToStub);

        sut.transfer(1, 2, 5);

        verify(accountFromStub).setAmount(5);
        verify(accountToStub).setAmount(25);
        verify(accountRepoStub).save(accountFromStub);
        verify(accountRepoStub).save(accountToStub);
    }

    @Test
    void shouldLogWhenCashAndAmountValid () {
        ClientRepository clientRepoStub = mock(ClientRepository.class);
        AccountRepository accountRepoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(accountRepoStub, clientRepoStub, cashStub);

        sut.cash(10.5, 1);

        verify(cashStub).log(10.5, 1);
    }
}
