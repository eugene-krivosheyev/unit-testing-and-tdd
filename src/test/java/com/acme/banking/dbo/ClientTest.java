package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreatedAndNotNullIdAndNotEmptyName() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName);
        //endregion

        //region then
        assertEquals(stubId, sut.getId());
        assertEquals(dummyName, sut.getName());
        //endregion
    }

    @Test
    public void shouldCreatedEmptyClientWhenCreatedAndNotNullIdAndEmptyName() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "";
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName);
        //endregion

        //region then
        assertNull(sut.getId());
        assertNull(sut.getName());
        //endregion
    }

    @Test
    public void shouldCreatedEmptyClientWhenCreatedAndNullIdAndEmptyName() {
        //region given
        UUID stubId = null;
        String dummyName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName);
        //endregion

        //region then
        assertNull(sut.getId());
        assertNull(sut.getName());
        //endregion
    }

    @Test
    public void shouldCreatedEmptyClientWhenCreatedAndNotNullIdAndNullName() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = null;
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName);
        //endregion

        //region then
        assertNull(sut.getId());
        assertNull(sut.getName());
        //endregion
    }
}
