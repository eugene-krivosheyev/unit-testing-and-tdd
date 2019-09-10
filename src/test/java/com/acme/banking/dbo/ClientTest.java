package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        //region then
        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));

        assertThat(sut.getName(),
                allOf(
                        equalTo("dummy client name"),
                        notNullValue()
                ));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateClientWhenNameIsEmpty() {
        String stubName = "";
        UUID stubId = UUID.randomUUID();

        new Client(stubId, stubName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateClientWhenIdIsNull() {
        String stubName = null;
        UUID stubId = UUID.randomUUID();

        new Client(stubId, stubName);
    }

    //TODO add test for removing and adding account to accounts of client
}
