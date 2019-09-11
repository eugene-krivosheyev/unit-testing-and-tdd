package com.acme.banking.dbo;

import com.acme.banking.dbo.builder.TestAccountRepository;
import com.acme.banking.dbo.builder.TestClientRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.dto.AccountDto;
import com.acme.banking.dbo.dto.ClientDto;
import com.acme.banking.dbo.error.AccountNotEnoughException;
import com.acme.banking.dbo.error.NotFoundException;
import com.acme.banking.dbo.repository.AccountsRepository;
import com.acme.banking.dbo.repository.ClientsRepository;
import com.acme.banking.dbo.service.ProcessingService;
import com.acme.banking.dbo.service.ProcessingServiceImpl;
import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class ProcessingTest {

    @Test
    public void shouldInvokeLogWhenProcessingCreated() {
        //region given
        final UUID stubId = UUID.randomUUID();
        final double stubAmount = 0.3;
        final Cash cash = spy(Cash.class);
        final ProcessingService service = new ProcessingServiceImpl(
                mock(AccountsRepository.class),
                mock(ClientsRepository.class),
                cash
        );
        //endregion

        //region when
        service.cash(stubAmount, stubId);
        //endregion

        //region then
        verify(cash, times(1)).log(stubAmount, stubId);
        //endregion
    }

    @Test
    public void shouldReturnsClientDtoWhenClientCreated() {
        //region given
        TestClientRepository clientBuilder = new TestClientRepository.Builder()
                .build();

        final ProcessingService service = new ProcessingServiceImpl(
                mock(AccountsRepository.class),
                clientBuilder.getRepository(),
                mock(Cash.class)
        );
        //endregion

        //region when
        ClientDto client = service.createClient(clientBuilder.getName());
        //endregion

        //region then
        assertSame(client.getId(), clientBuilder.getId());
        assertSame(client.getName(), clientBuilder.getName());
        //endregion
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowExceptionWhenFindByClientId() throws NotFoundException {
        //region given
        TestClientRepository clientBuilder = new TestClientRepository.Builder()
                .build();

        final ProcessingService service = new ProcessingServiceImpl(
                mock(AccountsRepository.class),
                clientBuilder.getRepository(),
                mock(Cash.class)
        );
        //endregion

        //region when
        service.getAccountsByClientId(clientBuilder.getId());
        //endregion
    }

    @Test
    public void shouldReturnsAccountsWhenFindByClientId() throws NotFoundException {
        //region given
        TestAccountRepository accountBuilder = new TestAccountRepository.Builder()
                .setTransferAmount(0.2)
                .build();

        final ProcessingService service = new ProcessingServiceImpl(
                accountBuilder.getRepository(),
                mock(ClientsRepository.class),
                mock(Cash.class)
        );
        //endregion

        //region when
        List<AccountDto> accounts = service.getAccountsByClientId(accountBuilder.getId());
        //endregion

        //region then
        Assertions.assertThat(accounts).isNotEmpty();
        assertSame(accounts.get(0).getId(), accountBuilder.getId());
        //endregion
    }

    @Test(expected = AccountNotEnoughException.class)
    public void shouldThrowExceptionWhenNotEnoughAmount() throws AccountNotEnoughException {
        //region given
        TestAccountRepository accountBuilder = new TestAccountRepository.Builder()
                .setTransferAmount(0.9)
                .build();

        final ProcessingService service = new ProcessingServiceImpl(
                accountBuilder.getRepository(),
                mock(ClientsRepository.class),
                mock(Cash.class)
        );
        final Account fromAccount = accountBuilder.getFromAccount();
        final Account toAccount = accountBuilder.getToAccount();
        //endregion

        //region when
        service.transfer(accountBuilder.getTransferAmount(), fromAccount.getId(), toAccount.getId());
        //endregion
    }

    @Test
    public void shouldTransferFromAccountToAccountWhenTransferAmount() throws AccountNotEnoughException {
        //region given
        TestAccountRepository accountBuilder = new TestAccountRepository.Builder()
                .setTransferAmount(0.2)
                .build();

        final ProcessingService service = new ProcessingServiceImpl(
                accountBuilder.getRepository(),
                mock(ClientsRepository.class),
                mock(Cash.class)
        );
        final AccountsRepository repository = accountBuilder.getRepository();
        final Account fromAccount = accountBuilder.getFromAccount();
        final Account toAccount = accountBuilder.getToAccount();
        //endregion

        //region when
        UUID transferId = service.transfer(accountBuilder.getTransferAmount(), fromAccount.getId(), toAccount.getId());
        //endregion

        //region then
        verify(repository, times(1)).findById(fromAccount.getId());
        verify(repository, times(1)).findById(toAccount.getId());
        assertSame(transferId, accountBuilder.getTransferId());
        //endregion
    }
}
