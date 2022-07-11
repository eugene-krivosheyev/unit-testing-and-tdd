package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ClientUnitTest {
    @Test
    @DisplayName("should ... when ...")
    public void shouldContainAccountWhenAddAccount() {
        Client sut = new Client(1, "dummy name");
        Account stubAccount = mock(SavingAccount.class);

        sut.addAccount(stubAccount);
        Assertions.assertTrue(sut.getAccounts().contains(stubAccount));
    }

    @Test
    public void shouldCallMethodInMockSavingAccountWhenClientPrintAccounts() {
        Client sut = new Client(1, "dummy name");
        Account mockAccount = mock(SavingAccount.class);
        sut.addAccount(mockAccount);

        // when(stubAccount.getClient()).thenReturn(null);
        sut.printAccounts();

        verify(mockAccount, atMost(1)).getAmount();
    }
}
