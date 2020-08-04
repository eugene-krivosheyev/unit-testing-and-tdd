package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        int stubId = 1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        not(0)
                ));

        assertThat(sut.getName(),
                allOf(
                        equalTo(stubName),
                        notNullValue(),
                        not("")
                ));
        //endregion
    }

    @Test
    public void shouldGetIdWhenCreated() {
        //region given
        int stubId = 1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        Assert.assertEquals(stubId, sut.getId());
        //endregion
    }

    @Test
    public void shouldGetNameWhenCreated() {
        //region given
        int stubId = 1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        Assert.assertEquals(stubName, sut.getName());
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCreatedWithInvalidId() {
        //region given
        int stubId = 0;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCreatedWithNullName() {
        //region given
        int stubId = 0;
        String stubName = null;
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion
    }
}
