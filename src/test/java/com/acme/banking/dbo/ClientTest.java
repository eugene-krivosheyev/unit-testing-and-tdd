package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

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
        assertTrue(sut.hasAccounts());
        //endregion
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenClientIdIsNull() {
        new Client(null, stubName);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenNameIsNull() {
        new Client(stubId, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNameIsEmpty() {
        new Client(stubId, "");
    }
}
