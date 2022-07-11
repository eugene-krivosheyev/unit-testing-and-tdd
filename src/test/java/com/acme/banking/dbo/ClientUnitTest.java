package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ClientUnitTest {

    private Client sut;

    @BeforeEach
    void setUp() {
        sut = new Client(1, "dummy name");
    }

    @Test
    public void shouldAddAccountWhenAllValid() {
        Account stubAccount = Mockito.mock(Account.class);

        when(stubAccount.getClient()).thenReturn(sut);

        sut.addAccount(stubAccount);

        assertTrue(sut.getAccounts().contains(stubAccount));
    }

    @Test
    public void shouldNotAddSavingAccountWhenAccountIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> sut.addAccount(null)
        );
    }

    @Test
    public void shouldNotAddSavingAccountWhenAccountWithOtherClient() {
        Client dummyClient = mock(Client.class);
        Account stubAccount = mock(Account.class);

        when(stubAccount.getClient()).thenReturn(dummyClient);

        assertThrows(
                IllegalArgumentException.class,
                () -> sut.addAccount(stubAccount)
        );
    }

    @Test
    public void shouldPrintAccountIds() {
        Account mockAccount = mock(Account.class);
        when(mockAccount.getClient()).thenReturn(sut);
        sut.addAccount(mockAccount);

        sut.printAccountIds();
        verify(mockAccount, times(1)).getId();
    }
}
