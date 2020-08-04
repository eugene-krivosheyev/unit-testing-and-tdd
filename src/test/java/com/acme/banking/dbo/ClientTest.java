package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given

        Integer sutId =  100;
        String sutName = "dummy client name";
        //endregion

        //region when subject under test
        Client sut = new Client(sutId,sutName);
        //endregion

        //region then
        assertEquals(sutId,sut.getId());
        assertEquals(sutName,sut.getName());

        /*
        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));*/
        //endregion
    }

    public void shouldNotCreateWhenIdIsNull () {
        //region given
        Integer stubId =  null;
        //endregion

        //Client

    }
}
