package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.ClientRepository;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProcessingTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private SavingAccount accountDoubler;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Account accountRepositoryDoubler;

    @Mock
    private ClientRepository clientRepositoryDoubler;

    @Mock
    private Cash cash;

    private Processing processing;

    @BeforeEach
    private void setUp() {
        processing = new Processing(cash, clientRepositoryDoubler, accountRepositoryDoubler);
    }

    @Test
    public void shouldSaveCorrectAccountsWhenTransfer() {
        Account from = mock(Account.class);
        Account to = mock(Account.class);

        when(clientRepositoryDoubler.getAccountByClientId(1)).thenReturn(from);
        when(clientRepositoryDoubler.getAccountByClientId(2)).thenReturn(to);

        processing.transfer(1, 2, 100);

        verify(accountRepositoryDoubler).save(from);
        verify(accountRepositoryDoubler).save(to);
    }

    @Test
    public void shouldSaveCorrectAccountsWhenCash() {
        double amount = 100d;
        int fromAccountId = 1;
        Account from = mock(Account.class);

        when(from.getAmount()).thenReturn(150d);
        when(clientRepositoryDoubler.getAccountByClientId(1)).thenReturn(from);

        processing.cash(amount, fromAccountId);

        verify(cash).log(amount, fromAccountId);
        verify(accountRepositoryDoubler).save(any(Account.class));
    }

    @Test
    public void shouldNotSaveAccountsWhenCashWIthLowBalance() {
        Account from = mock(Account.class);

        when(from.getAmount()).thenReturn(90d);
        when(clientRepositoryDoubler.getAccountByClientId(1)).thenReturn(from);

        assertThrows(RuntimeException.class, () -> {
            processing.cash(100, 1);
            verify(cash).log(100d, 1);
        });
    }

    @Test
    public void shouldGetAccountsWhenCalledByClientId() {
        Account from = mock(Account.class);

        Collection<Account> accounts = Collections.singletonList(from);

        when(clientRepositoryDoubler.getAccounts(1)).thenReturn(accounts);

        Collection<Account> actualAccounts = processing.getAccountsByClientId(1);

        assertEquals(accounts, actualAccounts);
    }
}
