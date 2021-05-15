package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class Client1Test {
    @Test
    public void shouldStoreArtifactsWhenCreated() {
        //region Given
        final int clientId = 1;
        final String clientName = "name";
        //endregion

        //region When
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region Then
        assertEquals( clientId,sut.getId());
        assertEquals(clientName, sut.getName());
        //endregion
    }

    @Test
    public void shouldNotCreatedWnenNegativeId() {
        final int negativeId = -1;
        final String dummy = "name";

        final IllegalArgumentException thrown = assertThrows
                (IllegalArgumentException.class,
                        () -> new Client(negativeId, dummy),
                        "id!");
    }

    @Test
    public void shouldNotCreatedWhenEmptyName() {
        final int dummy = 1;
        final String emptyName = "";

        final IllegalArgumentException thrown = assertThrows
                (IllegalArgumentException.class,
                        ()-> new Client(dummy, emptyName),
                        "name!");
    }

    @Test
    public void shouldNotCreatedWhenNullName() {
        final int dummy = 1;
        final String nullName = null;

        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummy, nullName),
                "name!");
    }
}
