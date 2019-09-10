package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ClientTest {

    private final UUID stubId = UUID.randomUUID();
    private final String stubName = "dummy client name";

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        assertThat(sut.getId(), equalTo(stubId));
        assertThat(sut.getName(), equalTo(stubName));
        assertEquals(0, sut.getAccounts().size());
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenClientIdNull() {
        new Client(null, stubName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNameNull() {
        new Client(stubId, null);
    }
}
