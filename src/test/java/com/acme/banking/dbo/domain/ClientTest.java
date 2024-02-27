package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void createClientWithEmptyNameThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, null));
    }


    @Test
    public void getIdFromClientReturnsExpectedValue(){
        assertEquals(clientId, sut.getId());
    }

    @Test
    public void getNameFromClientReturnsExpectedValue(){
        assertEquals(clientName, sut.getName());
    }

}