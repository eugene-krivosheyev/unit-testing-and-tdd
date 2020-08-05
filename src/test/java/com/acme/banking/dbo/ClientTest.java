package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ClientTest {
    public static final UUID ANY_ID = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        double anyDouble = 0.0;
        //endregion

        //region when
        Client sut = new Client(ANY_ID, "dummy client name");
        //endregion

        //region then
        assertThat(sut.getId(),
            allOf(
                equalTo(ANY_ID),
                notNullValue()
        ));
        //endregion
    }
}
