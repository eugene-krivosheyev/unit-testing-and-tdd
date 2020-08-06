package com.acme.banking.dbo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        String name = "name";
        //endregion

        //region when
        Client sut = new Client(stubId, name);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(stubId)),
                        hasProperty("name", equalTo(name))
                )
        );

        //endregion
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("name cant be null");
        UUID stubId = UUID.randomUUID();
        String name = null;
        Client sut = new Client(stubId, name);
    }

    @Test
    public void shouldNotCreateWhenIdIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("UUID cant be null");
        UUID stubId = null;
        String name = "name";
        Client sut = new Client(stubId, name);
    }
}
