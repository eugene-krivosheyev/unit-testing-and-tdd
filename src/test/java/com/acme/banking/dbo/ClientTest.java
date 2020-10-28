package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    private Client sut;

    public ClientTest() {
        System.out.println("Instance created");
    }

    @Before //@After //static @BeforeClass @AfterClass
    public void setUp() {
        this.sut = new Client(ID_STUB, "dummy client name");
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region then
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(ID_STUB)),
                hasProperty("name", is("dummy client name"))
        ));
        //endregion
    }


    @Test
    public void test2() {
        sut = new DbPersitenceAccountBuilder()
                .withId(1) //accumulate
                .withName("abc") //accumulate
            .build(); //new | mock()
    }
}
