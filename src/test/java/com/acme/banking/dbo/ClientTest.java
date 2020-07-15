package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ClientTest {
    private Client sut;
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        Long stubId = (long) Math.random();
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));
        assertThat(sut.getName(),
                allOf(
                        equalTo(stubName),
                        notNullValue()
                ));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenIdIsNull() {
        //region given
        Long stubId = null;
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenIdLessThenZero() {
        //region given
        Long stubId = (long) -1;
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenNameIsInNull() {
        //region given
        String stubName = null;
        //endregion

        //region when
        Client sut = new Client( (long) 1, stubName);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenNameIsEmpty() {
        //region given
        String stubName = "";
        //endregion

        //region when
        Client sut = new Client( (long) 1, stubName);
        //endregion
    }

}
