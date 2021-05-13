package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("Client class tests")
public class ClientTest {
    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNegative() {
        //region given
        final int clientId = -1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Executable sut = () -> new Client(clientId, clientName);
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, sut);
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        //region given
        final int clientId = 1;
        final String clientName = null;
        //endregion

        //region when
        Executable sut = () -> new Client(clientId, clientName);
        //endregion

        //region then
        assertThrows(IllegalArgumentException.class, sut);
        //endregion
    }
}
