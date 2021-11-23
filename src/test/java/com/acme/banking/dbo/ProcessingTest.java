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
    private ClientRepository clientRepoStub;
    private AccountRepository accountRepoStub;
    private Cash cashStub;
    private Processing sut;

    @BeforeEach
    void set(){
        clientRepoStub = mock(ClientRepository.class);
        accountRepoStub = mock(AccountRepository.class);
        cashStub = mock(Cash.class);
        sut = new Processing(accountRepoStub, clientRepoStub, cashStub);
    }

    @Test
    void shouldAddToRepositoryWhenCreateClient () {
        sut.createClient("aaa");

        verify(clientRepoStub).add("aaa");
    }

    @Test
    void shouldGetAccountsWhenClientExisted () {
        sut.getAccountsByClientId(1);

        verify(accountRepoStub).getAccountsByClientId(1);
    }

    @Test
    void shouldTransferWhenAccountsAndAmountValid () {
        Account accountFromStub = mock(Account.class);
        Account accountToStub = mock(Account.class);
        when(accountFromStub.getAmount()).thenReturn(10.);
        when(accountToStub.getAmount()).thenReturn(20.);
        when(accountRepoStub.getAccountById(1)).thenReturn(accountFromStub);
        when(accountRepoStub.getAccountById(2)).thenReturn(accountToStub);

        sut.transfer(1, 2, 5);

        verify(accountFromStub).getAmount();
        verify(accountToStub).getAmount();
        verify(accountFromStub).setAmount(5);
        verify(accountToStub).setAmount(25);
        verify(accountRepoStub).save(accountFromStub);
        verify(accountRepoStub).save(accountToStub);
    }

    @Test
    void shouldLogWhenCashAndAmountValid () {
        sut.cash(10.5, 1);

        verify(cashStub).log(10.5, 1);
    }
}
