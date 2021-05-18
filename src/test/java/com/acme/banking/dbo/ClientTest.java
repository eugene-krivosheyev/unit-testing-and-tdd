package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {

    public static final int CLIENT_ID = 1;

    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(CLIENT_ID, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(CLIENT_ID, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(CLIENT_ID)),
                hasProperty("name", is(clientName))
        ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", CLIENT_ID)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenNegativeClientId() {
        final int negativeClientId = -1;
        final String clientName = "dummy client name";

        assertThrows(IllegalArgumentException.class, () ->  new Client(negativeClientId, clientName), "id < 0");
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenEmptyClientName() {
        final String emptyClientName = "";

        assertThrows(IllegalArgumentException.class, () ->  new Client(CLIENT_ID, emptyClientName), "Bad name");
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenNullClientName() {
        final int clientId = 1;

        assertThrows(IllegalArgumentException.class, () ->  new Client(clientId, null), "Bad name");
    }


    @Test
    @DisplayName("Test case")
    public void clientShouldNotAddAlienAccount() {
        final double amount = 0.5;
        final int accountId = 1;
        final Client client = new Client(CLIENT_ID, "dummy client name");
        final Client secondClient = new Client(CLIENT_ID, "dummy client name");

        SavingAccount account = new SavingAccount(accountId, secondClient, amount);

        assertThrows(IllegalArgumentException.class, () -> client.addAccount(account), "Incorrect client! FRAUD!");
    }

    @Test
    @DisplayName("Test case")
    public void clientShouldContainManyAccount() {
        final double amount = 0.5;
        final int accountId = 1;
        final Client client = new Client(CLIENT_ID, "dummy client name");

        SavingAccount account = new SavingAccount(accountId, client, amount);
        SavingAccount secondAccount = new SavingAccount(accountId, client, amount);

        assertArrayEquals(client.getAccounts().toArray(), new SavingAccount[]{account, secondAccount});

    }
}
