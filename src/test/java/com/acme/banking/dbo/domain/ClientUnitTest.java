package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ClientUnitTest {
    @Test
    @DisplayName("should ... when ...")
    public void shouldContainAccountWhenAddAccount() {
        Client sut = new Client(1, "dummy name");
        Account dummyAccount = Mockito.mock(SavingAccount.class);

        sut.addAccount(dummyAccount);
        Assertions.assertTrue(sut.getAccounts().contains(dummyAccount));
    }

    @Test
    public void shouldClientContainAccountWithClientEqualItWhenClientAddAccount() {
        Client sut = new Client(1, "dummy name");
        Account stubAccount = Mockito.mock(SavingAccount.class);
        Mockito.when(stubAccount.getClient()).thenReturn(sut);

        sut.getAccounts();

        Assertions.assertAll("shouldClientContainAccountWithClientEqualItWhenClientAddAccount",
                ()->Assertions.assertTrue(sut.getAccounts().contains(stubAccount)),
                ()->Assertions.assertEquals(sut, stubAccount.getClient())
        );
    }

    @Test
    public void shouldCallMethodInMockSavingAccountWhenClientPrintAccounts() {
        Client sut = new Client(1, "dummy name");
        Account mockAccount = Mockito.mock(SavingAccount.class);
        sut.addAccount(mockAccount);

        // when(stubAccount.getClient()).thenReturn(null);
        sut.printAccounts();

        Mockito.verify(mockAccount, Mockito.atMost(1)).getAmount();
    }
}
