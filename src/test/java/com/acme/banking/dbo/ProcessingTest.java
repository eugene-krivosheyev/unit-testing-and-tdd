package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.dto.AccountDto;
import com.acme.banking.dbo.dto.ClientDto;
import com.acme.banking.dbo.errors.AccountNotEnoughException;
import com.acme.banking.dbo.errors.NotFoundException;
import com.acme.banking.dbo.repository.AccountsRepository;
import com.acme.banking.dbo.repository.ClientsRepository;
import com.acme.banking.dbo.service.ProcessingService;
import com.acme.banking.dbo.service.ProcessingServiceImpl;
import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProcessingTest {

    private final UUID stubId = UUID.randomUUID();
    private final double stubAmount = 0.3;
    private final String stubName = "StubName";

    private final AccountsRepository accountsRepositoryMock = mock(AccountsRepository.class);
    private final ClientsRepository clientsRepositoryMock = mock(ClientsRepository.class);
    private final Cash cash = spy(Cash.class);
    private final ProcessingService service = new ProcessingServiceImpl(accountsRepositoryMock, clientsRepositoryMock, cash);

    @Test
    public void shouldInvokeLogWhenProcessingCreated() {
        //region when
        service.cash(stubAmount, stubId);
        //endregion

        //region then
        verify(cash, times(1)).log(stubAmount, stubId);
        //endregion
    }

    @Test
    public void shouldReturnsClientWhenClientCreated() {
        //region given
        when(clientsRepositoryMock.create(stubName)).thenReturn(new Client(stubId, stubName));
        //endregion

        //region when
        ClientDto client = service.createClient(stubName);
        //endregion

        //region then
        assertSame(client.getId(), stubId);
        assertSame(client.getName(), stubName);
        //endregion
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowExceptionWhenFindByClientId() throws NotFoundException {
        //region given
        when(clientsRepositoryMock.create(stubName)).thenReturn(new Client(stubId, stubName));
        //endregion

        //region when
        service.getAccountsByClientId(stubId);
        //endregion
    }

    @Test
    public void shouldReturnsAccountsWhenFindByClientId() throws NotFoundException {
        //region given
        List<Account> stubAccounts = Collections.singletonList(new SavingAccount(stubId, stubAmount));
        when(accountsRepositoryMock.findAccountsByClientId(stubId)).thenReturn(stubAccounts);
        //endregion

        //region when
        List<AccountDto> accounts = service.getAccountsByClientId(stubId);
        //endregion

        //region then
        Assertions.assertThat(accounts).isNotEmpty();
        assertSame(accounts.get(0).getId(), stubId);
        //endregion
    }

    @Test(expected = AccountNotEnoughException.class)
    public void shouldThrowExceptionWhenNotEnoughAmount() throws AccountNotEnoughException {
        //region given
        double transferAmount = 0.9;
        Account fromAccount = new SavingAccount(UUID.randomUUID(), stubAmount);
        Account toAccount = new SavingAccount(UUID.randomUUID(), stubAmount);

        when(accountsRepositoryMock.findById(fromAccount.getId())).thenReturn(fromAccount);
        when(accountsRepositoryMock.findById(toAccount.getId())).thenReturn(toAccount);
        when(accountsRepositoryMock.transfer(transferAmount, fromAccount, toAccount)).thenReturn(stubId);
        //endregion

        //region when
        service.transfer(transferAmount, fromAccount.getId(), toAccount.getId());
        //endregion
    }

    @Test
    public void shouldTransferFromAccountToAccountWhenTransferAmount() throws AccountNotEnoughException {
        //region given
        double transferAmount = 0.2;
        Account fromAccount = new SavingAccount(UUID.randomUUID(), stubAmount);
        Account toAccount = new SavingAccount(UUID.randomUUID(), stubAmount);

        when(accountsRepositoryMock.findById(fromAccount.getId())).thenReturn(fromAccount);
        when(accountsRepositoryMock.findById(toAccount.getId())).thenReturn(toAccount);
        when(accountsRepositoryMock.transfer(transferAmount, fromAccount, toAccount)).thenReturn(stubId);
        //endregion

        //region when
        UUID transferId = service.transfer(transferAmount, fromAccount.getId(), toAccount.getId());
        //endregion

        //region then
        verify(accountsRepositoryMock, times(1)).findById(fromAccount.getId());
        verify(accountsRepositoryMock, times(1)).findById(toAccount.getId());
        assertSame(transferId, stubId);
        //endregion
    }
}
