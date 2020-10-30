package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.ClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {

    private static final AccountRepository accountRepository = mock(AccountRepository.class);
    private static final ClientRepository clientRepository = mock(ClientRepository.class);
    private static final Account fromAccount = mock(Account.class);
    private static final Account toAccount = mock(Account.class);
    private static final UUID uuid = UUID.randomUUID();
    private static final UUID uuid2 = UUID.randomUUID();
    private static final String dummy = "dummy";
    private static final Processing processing = new Processing(accountRepository, clientRepository);
    private static final int AMOUNT = 12;

    @Test
    public void shouldNotCreateWhenAccountRepositoryNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Processing(null, clientRepository));
    }

    @Test
    public void shouldNotCreateWhenClientRepositoryNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Processing(accountRepository, null));
    }

    @Test
    public void shouldNotCreateClientWhenNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            processing.createClient(null);
        });
    }

    @Test
    public void shouldNotCreateClientWhenNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            processing.createClient("");
        });
    }

    @Test
    public void shouldNotCreateClientWhenNameOnlySpace() {
        assertThrows(IllegalArgumentException.class, () -> new Processing(accountRepository, clientRepository).createClient("   "));
    }

    @Test
    public void shouldGetUuidWhenCreateClient() {
        when(clientRepository.createClient(dummy)).thenReturn(uuid);
        assertEquals(uuid, processing.createClient(dummy));
    }

    @Test
    public void shouldNotCreateAccountsWhenUuidNull() {
        assertThrows(IllegalArgumentException.class, () -> processing.getAccountsByClientId(null));
    }

    @Test
    public void shouldCreateAccountsWhenGetAccountsByClientId() {
        when(processing.getAccountsByClientId(uuid)).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), processing.getAccountsByClientId(uuid));
    }

    @Test
    public void shouldTransfer(){
        when(accountRepository.findById(uuid)).thenReturn(fromAccount);
        when(accountRepository.findById(uuid2)).thenReturn(toAccount);

        processing.transfer(AMOUNT, uuid, uuid2);

        verify(fromAccount).withdraw(AMOUNT);
        verify(toAccount).deposit(AMOUNT);
    }

    @Test
    public void shouldNotTransferWhenNotFoundAccount(){

        when(accountRepository.findById(uuid)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> processing.transfer(AMOUNT, uuid, uuid2));


    }

}

