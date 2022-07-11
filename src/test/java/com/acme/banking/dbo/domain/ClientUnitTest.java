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
        Account dummyAccount = mock(SavingAccount.class);

        sut.addAccount(dummyAccount);
        Assertions.assertTrue(sut.getAccounts().contains(dummyAccount));
    }

    @Test
    public void shouldClientContainAccountWithClientEqualItWhenClientAddAccount() {
        Client sut = new Client(1, "dummy name");
        Account stubAccount = mock(SavingAccount.class);
        when(stubAccount.getClient()).thenReturn(sut);

        sut.getAccounts();

        Assertions.assertAll("shouldClientContainAccountWithClientEqualItWhenClientAddAccount",
                ()->Assertions.assertTrue(sut.getAccounts().contains(stubAccount)),
                ()->Assertions.assertEquals(sut, stubAccount.getClient())
        );
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
