package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;


import static org.junit.Assert.*;


public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        Integer stubId = 1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        assertEquals(stubId,sut.getId());
        assertEquals(stubName,sut.getName());
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNegative() {
        //region given
        Integer stubId = -1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region Assert | Then
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull() {
        //region given
        Integer stubId = 1;
        String stubName = null;
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region Assert | Then
        //endregion
    }



}
