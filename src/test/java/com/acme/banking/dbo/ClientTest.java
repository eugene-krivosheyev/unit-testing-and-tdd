package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ClientTest {
    final String dummy_client_name = "dummy client name";

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        long stubId = 1;
        //endregion

        //region when
        Client sut = new Client(stubId, dummy_client_name);
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        notNullValue(),
                        equalTo(stubId)
                ));

        assertThat(sut.getName(),
                allOf(
                        notNullValue(),
                        equalTo(dummy_client_name)
                ));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenIdIsLessThanZero() {
        long stubId = -1;

        new Client(stubId, dummy_client_name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenNameIsNull() {
        long stubId = 1;

        new Client(stubId, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenNameIsEmpty() {
        long stubId = 1;

        new Client(stubId, "");
    }
}
