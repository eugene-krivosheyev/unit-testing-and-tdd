package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
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
                equalTo(stubId),
                notNullValue()
        ));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenIdIsLessThanZero(){
        long stubId = -1;

        Client sut = new Client(stubId, dummy_client_name);
    }
}
