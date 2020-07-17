package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import static org.apache.commons.lang.math.RandomUtils.nextLong;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class ClientTest {

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        long stubId = nextLong();
        String stubName = "stubName";
        //endregion

        //region when
        Client sut = new Client(stubId, "stubName");
         //endregion

        //region then
        assertThat(sut.getId(),
        allOf(
        equalTo(stubId),
        notNullValue()
        ));

        assertThat(sut.getName(),
        allOf(
        equalTo(stubName),
        notNullValue()
        )
        );

        //endregion
        }


@Test(expected = IllegalArgumentException.class)
public void shouldGetErrorWhenCreatedWithNullId() {
        Client sut = new Client(null, "someName");
        }

@Test(expected = IllegalArgumentException.class)
public void shouldGetErrorWhenCreatedWithNullName() {
        Client sut = new Client((long) 2, null);
        }

@Test(expected = IllegalArgumentException.class)
public void shouldGetErrorWhenCreatedWithNegativeId() {
        Client sut = new Client((long) -3445, "someName");
        }

@Test(expected = IllegalArgumentException.class)
public void shouldGetErrorWhenCreatedWithEmptyName() {
        Client sut = new Client((long) 2, "");
        }
        }
