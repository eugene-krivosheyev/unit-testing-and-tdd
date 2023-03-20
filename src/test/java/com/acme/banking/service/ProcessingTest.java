package com.acme.banking.service;

import java.util.Collection;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProcessingTest {

    @Mock
    private ClientRepository mockClientRepository;
    @Mock
    private AccountRepository mockAccountRepository;
    @InjectMocks
    private Processing sut;
    @Captor
    ArgumentCaptor<Client> clientArgumentCaptor;

    @Test
    void shouldCreateClientWhenValidClientName() {
        //given
        final String name = "dummy client";
        final int id = 1;
        when(mockClientRepository.getLastClientId()).thenReturn(id);

        //when
        Client client =  sut.createClient(name);

        //then
        verify(mockClientRepository).getLastClientId();
        verify(mockClientRepository).saveClient(clientArgumentCaptor.capture());
        Client capturedClient = clientArgumentCaptor.getValue();
        assertAll (
                () -> assertEquals(id+1, capturedClient.getId()),
                () -> assertEquals(name, capturedClient.getName())
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenInValidClientName() {
        //given
        final String name = " ";
        final int id = 1;
        when(mockClientRepository.getLastClientId()).thenReturn(id);

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sut.createClient(name))
                .withMessage("name should not be null or empty");
        verifyNoMoreInteractions(mockClientRepository);
    }

    @Test
    void shouldGetAccountsWhenValidClientIdWithAccount() {
        //given
        final int id = 1;
        final String name = "dummy client";
        final Client client = new Client(id, name);
        final int accountId = 1;
        final double amount = 1.00;
        final SavingAccount account = new SavingAccount(accountId, client, amount);
        when(mockClientRepository.getClientById(id)).thenReturn(client);

        //when
        Collection<Account> accounts = sut.getAccountsByClientId(id);

        //then
        assertTrue(accounts.contains(account));
        verify(mockClientRepository).getClientById(id);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenClientIdIsInvalid() {
        //given
        final int id = 1;
        when(mockClientRepository.getClientById(id)).thenReturn(null);

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sut.getAccountsByClientId(id))
                .withMessage("Client does not exist");
        verify(mockClientRepository).getClientById(id);
    }

    @ParameterizedTest
    @ValueSource(doubles = {10.00, 5.00})
    void shouldTransferAmountWhenValidAccountsAndAmountsAreGiven(double amount) {
        //given
        final double amountInAccountFrom = 10.00;
        final double amountInAccountTo = 0.00;
        Account accountFrom = mock(Account.class);
        Account accountTo = mock(Account.class);
        when(mockAccountRepository.findAccountById(1)).thenReturn(accountFrom);
        when(mockAccountRepository.findAccountById(2)).thenReturn(accountTo);
        when(accountFrom.getAmount()).thenReturn(amountInAccountFrom);

        //when
        sut.transfer(1, 2, amount);

        //then
        verify(accountFrom).setAmount(amountInAccountFrom - amount);
        verify(accountTo).setAmount(amountInAccountTo + amount);
        verify(mockAccountRepository).saveAccount(accountFrom);
        verify(mockAccountRepository).saveAccount(accountTo);
    }

    @Test
    void shouldGiveIllegalArgumentExceptionWhenAccountFromDoesNotExist() {
        //given
        final double amount = 10.00;
        when(mockAccountRepository.findAccountById(1)).thenReturn(null);

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sut.transfer(1, 2, amount))
                .withMessage("AccountFrom does not exist");
        verify(mockAccountRepository).findAccountById(1);
    }

    @Test
    void shouldGiveIllegalArgumentExceptionWhenAccountToDoesNotExist() {
        //given
        final double amount = 10.00;
        Account accountFrom = mock(Account.class);
        when(mockAccountRepository.findAccountById(1)).thenReturn(accountFrom);
        when(mockAccountRepository.findAccountById(2)).thenReturn(null);

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sut.transfer(1, 2, amount))
                .withMessage("AccountTo does not exist");
        verify(mockAccountRepository).findAccountById(1);
        verify(mockAccountRepository).findAccountById(2);
    }

    @Test
    void shouldGiveIllegalArgumentExceptionWhenAccountFromDoesNotHaveEnoughAmount() {
        //given
        final double amountInAccountFrom = 10.00;
        Account accountFrom = mock(Account.class);
        Account accountTo = mock(Account.class);
        when(mockAccountRepository.findAccountById(1)).thenReturn(accountFrom);
        when(mockAccountRepository.findAccountById(2)).thenReturn(accountTo);
        when(accountFrom.getAmount()).thenReturn(amountInAccountFrom);

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sut.transfer(1, 2, 11.00))
                .withMessage("Not enough amount in accountFrom");
        verify(mockAccountRepository).findAccountById(1);
        verify(mockAccountRepository).findAccountById(2);
    }
}
