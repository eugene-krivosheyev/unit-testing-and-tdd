package com.acme.banking.dbo.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        final long id = 1L;
        final String name = "dummy client name";

        Client sut = new Client(id, name);

        assertThat(sut.getId(), equalTo(id));
        assertThat(sut.getName(), equalTo(name));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenIdIsNull() {
        new Client(null,"name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenIdIsNegative() {
        new Client(-1L,"name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenNameIsNull() {
        new Client(0L,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenNameIsEmpty() {
        new Client(0L,"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenNameIsRestricted() {
        new Client(0L,"Bill Gates");
    }
}
