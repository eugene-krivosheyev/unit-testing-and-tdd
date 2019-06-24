package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ClientTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgExceptionWhenClientCreatedWithNullId() {
        //given
        String name = "Abba";
        //when
        new Client(null, name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgExceptionWhenClientCreatedWithNullName() {
        //given
        UUID id = UUID.randomUUID();
        //when
        new Client(id,  null);
    }

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
        //endregion
    }
}
