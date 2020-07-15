package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ClientTest {
    @Test(expected = IllegalArgumentException.class )
    public void shouldThrowExceptionWhenIdIsNull() {

        Long stubId = null; //UUID.randomUUID();
        Client sut = new Client(stubId, "dummy client name");

    }


    @Test(expected = IllegalArgumentException.class )
    public void shouldThrowExceptionWhenIdIsNegative() {

        Long stubId = Long.valueOf(-1); //UUID.randomUUID();
        Client sut = new Client(stubId, "dummy client name");

    }

    @Test(expected = IllegalArgumentException.class )
    public void shouldThrowExceptionWhenNameIsNull() {

        String stubName = null; //UUID.randomUUID();
        Client sut = new Client( 1L, stubName );
    }


    @Test(expected = IllegalArgumentException.class )
    public void shouldThrowExceptionWhenNameIsEmpty() {

        String stubName = ""; //UUID.randomUUID();
        Client sut = new Client( 1L, stubName );
    }

    @Test
    public void shouldReturnIdandNameWhichEqualToInput() {

        long id = 1L;
        String name = "ClientName";
        Client sut = new Client( id, name );

        assertEquals( id,  sut.getId() );
        assertEquals( name, sut.getName() );

    }




}

