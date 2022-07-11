package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ClientUnitTest {
    private Client sut;
    @BeforeEach
    public void setUp(){
        sut = new Client(1, "dummy name");
    }
    @Test
    @DisplayName("should ... when ...")
    public void shouldContainAccountWhenAddAccount() {
        Account dummyAccount = new MockitoAccountBuilder().build();

        sut.addAccount(dummyAccount);
        Assertions.assertTrue(sut.getAccounts().contains(dummyAccount));
    }

    @Test
    public void shouldClientContainAccountWithClientEqualItWhenClientAddAccount() {
        Account stubAccount = new MockitoAccountBuilder().widthClient(sut).build();

        sut.addAccount(stubAccount);

        Assertions.assertAll("shouldClientContainAccountWithClientEqualItWhenClientAddAccount",
                ()->Assertions.assertTrue(sut.getAccounts().contains(stubAccount)),
                ()->Assertions.assertEquals(sut, stubAccount.getClient())
        );
    }

    @Test
    public void shouldCallMethodInMockSavingAccountWhenClientPrintAccounts() {
        Account mockAccount = new MockitoAccountBuilder().build();
        sut.addAccount(mockAccount);

        sut.printAccounts();

        Mockito.verify(mockAccount, Mockito.atMost(1)).getAmount();
    }
}
