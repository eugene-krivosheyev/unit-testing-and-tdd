package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Test suite")
public class ClientTest {

    private final int id = 5;
    private final String name = "name";

    @Test
    public void shouldCreateClientWithValidArgs() {
        var resultClient = new Client(id, name);

        assertAll(
                () -> assertEquals(id, resultClient.getId()),
                () -> assertEquals(name, resultClient.getName())
        );
    }

    @Test
    public void shouldThrowWhenCreateClientWithInvalidArgs() {

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Client(-5, name)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Client(id, null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Client(id, "   "))
        );
    }

    @Test
    public void shouldAddAccountSuccessful(){

        var client = new Client(id, name);
        var account = new SavingAccount(1, client, 1);

        client.addAccount(account);

        assertAll(
                () -> assertEquals(1, client.getAccountsSize()),
                () -> assertEquals(account, client.getAccount(account.getId()).get())
        );
    }

    @Test
    public void shouldNotAddAccountAndThrowWhenStateArgumentIncorrect(){
        var client = new Client(id, name);
        var clientSecond = new Client(11, name);

        var account = new SavingAccount(1, clientSecond, 1);

        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> client.addAccount(account)),
                () -> assertEquals(0, client.getAccountsSize())
        );

    }

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
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }
}
