package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {

    @Test
    public void shouldNotCreateWhenNegativeID() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new Client(-1, ""));
        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> new Client(-1, "Test"));
        assertEquals("ID must be positive", e1.getMessage());
        assertEquals("ID must be positive", e2.getMessage());
    }

    @Test
    public void shouldNotCreateWhenNullName() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new Client(1, null));
        assertEquals("Name cannot be null.", e1.getMessage());
    }

    @Test
    public void shouldNotCreateWhenEmptyName() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new Client(1, ""));
        assertEquals("Name cannot be empty.", e1.getMessage());
    }

    @Test
    public void shouldNotCreateWhenNameLengthMoreThan50() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
                () -> new Client(1, "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"));
        assertEquals("Name length must be <= 30.", e1.getMessage());
    }

    @Test
    public void shouldCreateWhenNameLengthLessThanOrEqual50AndIdIsPositive() {
        Client client1 = new Client(1, "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");

        assertEquals(1, client1.getId());
        assertEquals("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", client1.getName());
    }

//    @Test @Disabled("temporary disabled")
//    @DisplayName("Test case")
//    public void shouldStorePropertiesWhenCreated() {
//        //region given
//        final int clientId = 1;
//        final String clientName = "dummy client name";
//        //endregion
//
//        //region when
//        Client sut = new Client(clientId, clientName);
//        assumeTrue(sut != null);
//        //endregion
//
//        //region then
//        //Junit5:
//        assertAll("Client store its properties",
//                () -> assertEquals(clientId, sut.getId()),
//                () -> assertEquals(clientName, sut.getName())
//        );
//
//        //Hamcrest:
//        assertThat(sut,
//            allOf(
//                hasProperty("id", notNullValue()),
//                hasProperty("id", equalTo(clientId)),
//                hasProperty("name", is(clientName))
//        ));
//
//        //AssertJ:
//        org.assertj.core.api.Assertions.assertThat(sut)
//                .hasFieldOrPropertyWithValue("id", clientId)
//                .hasFieldOrPropertyWithValue("name", clientName);
//        //endregion
//    }
}
