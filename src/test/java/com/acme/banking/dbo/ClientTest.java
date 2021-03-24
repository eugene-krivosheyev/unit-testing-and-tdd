package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Test suite")
//@ExtendWith(MockitoExtension.class)
public class ClientTest {
//    @Mock Object stub;

    @Test @Disabled //@Ignore
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        Assertions.assertNotEquals(.3, .1 + .2, "Surprise!!!");
        Assertions.assertSame("a", "a"); // o1 == o2

        //комбинатор
        assertAll("Client successfully store its properties",
//                noName() { assertEquals(clientId, sut.getId()) }
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest: matchers
        assertThat(sut,
            allOf( //anyOf
                not(hasProperty("id", nullValue())),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", is(clientName))
        ));
    }

//        //AssertJ: fluent API
//        org.assertj.core.api.Assertions.assertThat(sut)
//                .isNotNull()
//                .hasFieldOrPropertyWithValue("id", clientId)
//                .hasFieldOrPropertyWithValue("name", clientName);
//        //endregion
    @Test
    public void idShuntBeZero() {
        final int clientId = 0;
        final String clientName = "dummy client name";
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void nameShuntBeNull() {
        final int clientId = 1;
        final String clientName = null;
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldGetErrorWhenCreateWithNullName() {
        final int DUMMY_ID = 1;

        final IllegalArgumentException expectedException = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(DUMMY_ID, null)
        );

        assertEquals("name is not null", expectedException.getMessage());
    }
}
