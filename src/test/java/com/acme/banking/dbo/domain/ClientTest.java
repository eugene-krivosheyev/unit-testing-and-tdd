package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    final int clientId = 1;
    final String clientName = "Test Name";

    Client sut;

    @BeforeEach
    void createSUT(){
        sut = new Client(clientId, clientName);
    }

    @Test
    public void createClientWithInvalidIdThrowsIllegalArgumentException(){
        int invalidId = -1;
        assertThrows(IllegalArgumentException.class, () -> new Client(invalidId, clientName));
    }

    @Test
    public void createClientWithNullNameThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, null));
    }

    @Test
    public void createClientWithEmptyNameThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, ""));
    }

    @Test
    public void getIdFromClientReturnsExpectedValue(){
        assertEquals(clientId, sut.getId());
    }

    @Test
    public void getNameFromClientReturnsExpectedValue(){
        assertEquals(clientName, sut.getName());
    }

    @Test
    public void shouldAddExistingAccountForCurrentClient(){
        Account account = new SavingAccount(1, sut, 500);
        sut.addAccount(account);
        assertAll("Check addAccount() method and check equals account and accounts element",
                () -> assertEquals(1, sut.getAccounts().size()),
                () -> assertEquals(sut,sut.getAccounts().stream().findFirst().get().getClient())
        );
    }

    @Test
    public void shouldThrowExceptionWhenAddInvalidAccount(){
        Client otherClient = new Client(2, "Other Client");
        Account account = new SavingAccount(3, otherClient, 50);
        assertThrows(IllegalStateException.class, () -> sut.addAccount(account));
    }

}