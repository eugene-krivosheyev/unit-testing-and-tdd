package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
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
    public void shouldSavePropertiesIdAndNameWhenCreatedClient() {
        //region given

        Integer sutId =  1; //очевидно первое 0 или 1
        String sutName = "dummy client name"; // очевидное название
        //endregion

        //region when subject under test
        Client sut = new Client(sutId,sutName);
        //endregion

        //region when subject under test
        assertEquals(sutId,sut.getId());
        assertEquals(sutName,sut.getName());
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull () {
        //region given
        Integer sutId =  null;
        String sutName = "dummy client name";
        //endregion

        //region when subject under test
        Client sut = new Client(sutId, sutName);
        //endregion

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenNameIsNull () {
        //region given
        Integer sutId =  123;
        String sutName = null;
        //endregion

        //region when subject under test
        Client sut = new Client(sutId, sutName);
        //endregion 
    }


}
