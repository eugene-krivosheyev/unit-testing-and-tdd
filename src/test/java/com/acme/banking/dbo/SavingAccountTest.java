package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {

    @Test
    public void shouldNotCreateWhenNegativeOrZeroID() {
        Client client = new Client(1, "test");
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, client, 1.1));
        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, null, 1.1));
        IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, client, -1));

        assertEquals("ID must be positive", e1.getMessage());
        assertEquals("ID must be positive", e2.getMessage());
        assertEquals("ID must be positive", e3.getMessage());

        e1 = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(0, client, 1.1));
        e2 = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(0, null, 1.1));
        e3 = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(0, client, -1));

        assertEquals("ID must be positive", e1.getMessage());
        assertEquals("ID must be positive", e2.getMessage());
        assertEquals("ID must be positive", e3.getMessage());
    }

    @Test
    public void shouldNotCreateWhenNullClient() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, null, 1.1));
        assertEquals("Client cannot be null.", e1.getMessage());
    }

    @Test
    public void shouldNotCreateWhenAmountIsNegative() {
        Client client = new Client(1, "test");

        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, client, -0.1));
        assertEquals("Amount must be >= 0.", e1.getMessage());
    }

   @Test
    public void shouldCreateWhenIdIsPositiveAndClientNotNull() {
        Client client = new Client(1, "test");
        SavingAccount account = new SavingAccount(1, client, 1.1);

        assertEquals(1, account.getClient().getId());
        assertEquals("test", account.getClient().getName());
        assertEquals(1, account.getId());
        assertEquals(1.1, account.getAmount());
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
