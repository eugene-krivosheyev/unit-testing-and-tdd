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
//    @Test
//    public void shouldSavePropertiesWhenCreated() {
//        //region given
//        UUID stubId = UUID.randomUUID();
//        //endregion
//
//        //region when
//        Client sut = new Client(stubId, "dummy client name");
//        //endregion
//
//        //region then
//        assertThat(sut.getId(),
//            allOf(
//                equalTo(stubId),
//                notNullValue()
//        ));
//        //endregion
//    }

    @Test
    public void shouldGetClientIdAndClientNameWhenCreatedWithCorrectParams() {
        Client sut = new Client(1, "TestName");
        assertEquals("TestName", sut.getName());
        assertEquals(1,sut.getId());
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldGetErrorGetWhenIdIsLowerThanZero() {
        Client sut = new Client(-1, "TestName");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldGetErrorGetWhenNameIsNull() {
        Client sut = new Client(1, null);
    }
}
