package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

public class SavingAccountTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

//    @Test
//    public void shouldStorePropertiesWhenCreated() {
//        //region when
//        Client sut = new Client(ID_STUB, "dummy client name");
//        //endregion
//
//        //region then
//        assertThat(sut,
//            allOf(
//                hasProperty("id", notNullValue()),
//                hasProperty("id", equalTo(ID_STUB)),
//                hasProperty("name", is("dummy client name"))
//        ));
//        //endregion
//    }
}
