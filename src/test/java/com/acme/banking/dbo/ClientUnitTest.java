package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ClientUnitTest {

    @Test
    public void shouldAddAccountWhenAllValid() {
        Client sut = new Client(1, "dummy name");
        Account stubAccount = Mockito.mock(SavingAccount.class);

        Mockito.when(stubAccount.getClient()).thenReturn(sut);

        sut.addAccount(stubAccount);

        assertTrue(sut.getAccounts().contains(stubAccount));
    }

    @Test
    public void shouldNotAddSavingAccountWhenAccountIsInvalid() {
        Client sut = new Client(1, "dummy name");

        assertAll(
                "Adding invalid SaveAccount",
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> sut.addAccount(null)
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            Client dummyClient = mock(Client.class);
                            SavingAccount stubAccount = mock(SavingAccount.class);

                            when(stubAccount.getClient()).thenReturn(dummyClient);

                            sut.addAccount(stubAccount);
                        }
                )
        );
    }

    @Test
    public void shouldPrintAccountIds() {
        Client sut = new Client(1, "dummy name");
        Account mockAccount = mock(SavingAccount.class);
        when(mockAccount.getClient()).thenReturn(sut);
        sut.addAccount(mockAccount);

        sut.printAccountIds();
        verify(mockAccount, times(1)).getId();
    }
}
