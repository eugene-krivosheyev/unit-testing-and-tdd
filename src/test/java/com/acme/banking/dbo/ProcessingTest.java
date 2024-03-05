package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;

import java.util.Collection;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class ProcessingTest {

    @Test
    public void shouldSaveClientInRepository() {
        // Given:
        var stubClientRepository = mock(ClientRepository.class);
        var sut = new Processing(stubClientRepository);
        Client client = new Client(1, "client1");
        when(stubClientRepository.save(argThat((Client cl) -> Objects.equals(cl.getName(), client.getName()))))
                .thenReturn(client);

        // When
        Client createdClient = sut.createClient("client1");

        // Then
        Assertions.assertEquals(1, createdClient.getId());
        Assertions.assertEquals("client1", createdClient.getName());
    }

    @Test
    public void shouldReturnEmptyAccountsWhenClientHasNotAccount() {
        var stubClientRepository = mock(ClientRepository.class);
        var sut = new Processing(stubClientRepository);
        Client client = new Client(1, "client1");
        when(stubClientRepository.findClientById(anyInt())).thenReturn(client);

        Collection<Account> accounts = sut.getAccountsByClientId(1);

        Assertions.assertTrue(accounts.isEmpty());
    }

    @Test
    public void shouldReturnCorrectAccountsWhenClientHasOneAccount() {
        var stubClientRepository = mock(ClientRepository.class);
        var sut = new Processing(stubClientRepository);
        Client client = new Client(1, "client1");
        Account account = new SavingAccount(1, client, 100);
        client.addAccount(account);
        when(stubClientRepository.findClientById(anyInt())).thenReturn(client);

        Collection<Account> accounts = sut.getAccountsByClientId(1);

        Assertions.assertTrue(accounts.contains(account));
    }

    @Test
    public void shouldCorrectlyTransferMoneyFromAccountToAccount() {
        var stubAccountRepository = mock(AccountRepository.class);
        Client client = new Client(1, "client1");
        Account fromAccount = new SavingAccount(1, client, 100);
        Account toAccount = new SavingAccount(2, client, 100);
        var sut = new Processing(stubAccountRepository);
        when(stubAccountRepository.findByAccountId(1)).thenReturn(fromAccount);
        when(stubAccountRepository.findByAccountId(2)).thenReturn(toAccount);

        // when
        sut.transfer(fromAccount.getId(), toAccount.getId(), 50);

        Assertions.assertEquals(50, fromAccount.getAmount());
        Assertions.assertEquals(150, toAccount.getAmount());

        verify(stubAccountRepository).save(fromAccount);
        verify(stubAccountRepository).save(toAccount);
    }

    @Test
    public void shouldNotTransferMoneyFromAccountToAccount(){
        var stubAccountRepository = mock(AccountRepository.class);
        Client client = new Client(1, "client1");
        Account fromAccount = new SavingAccount(1, client, 100);
        Account toAccount = new SavingAccount(2, client, 100);
        var sut = new Processing(stubAccountRepository);
        when(stubAccountRepository.findByAccountId(1)).thenReturn(fromAccount);
        when(stubAccountRepository.findByAccountId(2)).thenReturn(toAccount);

        // when
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sut.transfer(fromAccount.getId(), toAccount.getId(), 200));

        verify(stubAccountRepository, times(0)).save(fromAccount);
        verify(stubAccountRepository, times(0)).save(toAccount);

    }

    @Test
    public void shouldLogWhenCash(){
        Cash cashMock = mock(Cash.class);
        var sut = new Processing(cashMock);
        //when
        sut.cash(1,1);
        //then
        verify(cashMock).log(1,1);
    }


}
