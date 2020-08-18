package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;



public class ClientTest {

    private final String stubName = "dummy client name";
    private final Integer stubId = 1;
    private Client sut;

    @Before
    public void setUp() {
        sut = new Client(stubId, stubName);
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
//        Integer stubId = 1;
//        String stubName = "dummy client name";
        //endregion

        //region when
//        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        assertThat("Свойства объекта должны сохраниться",sut,
                allOf(
                        hasProperty("id", is(stubId)),
                        hasProperty("name", is(stubName))
                )
        );
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNegative() {
        //region given
        Integer stubId = -1;
//        String stubName = "dummy client name";
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
        Integer stubId = null;
//        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region Assert | Then
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenNameIsNull() {
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenNameIsEmpty() {
        //region given
        Integer stubId = 1;
        String stubName = "";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region Assert | Then
        //endregion
    }


}
