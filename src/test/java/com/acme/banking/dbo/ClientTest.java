package com.acme.banking.dbo;

import com.acme.banking.dbo.builder.TestEntities;
import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ClientTest {

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        TestEntities builder = new TestEntities.Builder().build();
        Client sut = builder.getClient();
        //endregion

        //region then
        assertThat(sut.getId(), equalTo(builder.getId()));
        assertThat(sut.getName(), equalTo(builder.getName()));
        assertTrue(sut.hasAccounts());
        //endregion
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenClientIdIsNull() {
        new Client(null, "Name");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenNameIsNull() {
        new Client(UUID.randomUUID(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNameIsEmpty() {
        new Client(UUID.randomUUID(), "");
    }
}
