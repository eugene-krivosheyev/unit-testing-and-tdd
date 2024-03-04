package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessingTest {

    @Test
    public void shouldSaveClientInRepository() {
        // Given:
        var stubClientRepository = mock(ClientRepository.class);
        var sut = new Processing(stubClientRepository);
        when(stubClientRepository.save(any())).thenReturn(new Client(1, "client1"));

        // When
        Client createdClient = sut.createClient("petr");

        // Then
        Assertions.assertEquals(createdClient.getId(), 1);
        Assertions.assertEquals(createdClient.getName(), "client1");
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
    public void shouldReturnEmptyAccountsWhenClientHasOneAccount() {
        var stubClientRepository = mock(ClientRepository.class);
        var sut = new Processing(stubClientRepository);
        Client client = new Client(1, "client1");
        Account account = new SavingAccount(1, client, 100);
        client.addAccount(account);
        when(stubClientRepository.findClientById(anyInt())).thenReturn(client);

        Collection<Account> accounts = sut.getAccountsByClientId(1);

        Assertions.assertTrue(accounts.contains(account));
    }


}
