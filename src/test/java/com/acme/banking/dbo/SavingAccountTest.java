package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class SavingAccountTest {
    @Test
    @Disabled("temporary disabled")
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
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(clientId)),
                        hasProperty("name", is(clientName))
                ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }

    @Test
    public void shouldNotCreateWhenIdIsNegative() {
        final int negativeId = -1;
        final Client dummyClient = new Client(1, "name");
        final double dummyAmount = 10;
        final String exceptionMessage = "id is negative";

        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(negativeId, dummyClient, dummyAmount)
        );
        assertEquals(exceptionMessage, thrown.getMessage());
    }

    @Test
    public void shouldNotCreateWhenClientIsNull() {
        final int dummyId = 1;
        final Client nullClient = null;
        final double dummyAmount = 10;
        final String exceptionMessage = "client is null";

        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyId, nullClient, dummyAmount)
        );
        assertEquals(exceptionMessage, thrown.getMessage());
    }

    @Test
    public void shouldNotCreateWhenAmountIsNegative() {
        final int dummyId = 1;
        final Client dummyClient = new Client(1, "name");
        final double negativeAmount = -10;
        final String exceptionMessage = "negative amount";

        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyId, dummyClient, negativeAmount)
        );
        assertEquals(exceptionMessage, thrown.getMessage());
    }

    @Test
    public void shouldCreateWhenParamsAreValid() {
        final int dummyId = 1;
        final Client client = new Client(1, "name");
        final double dummyAmount = 10;

        final SavingAccount sut = new SavingAccount(dummyId, client, dummyAmount);

        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", dummyId)
                .hasFieldOrPropertyWithValue("client", client)
                .hasFieldOrPropertyWithValue("amount", dummyAmount);

        org.assertj.core.api.Assertions.assertThat(client.getAccounts()).contains(sut);
    }
}
