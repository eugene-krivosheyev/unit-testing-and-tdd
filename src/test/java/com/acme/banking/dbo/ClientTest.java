package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Test suite")
public class ClientTest {
    private static final String VALID_CLIENT_NAME = "Ivan";
    private static final int VALID_CLIENT_ID = 1;

    Client client;

    @BeforeEach
    void init() {
        client = new Client(VALID_CLIENT_ID, VALID_CLIENT_NAME);
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
        org.hamcrest.MatcherAssert.assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(clientId)),
                        hasProperty("name", is(clientName))
                ));

        //AssertJ:
        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @Test
    void shouldAddAccountWhenComeValidAccount() {
        SavingAccount savingAccount = new SavingAccount(1, client, 1);

        client.addAccount(savingAccount);

        assertThat(client.getAccounts())
                .hasSize(1)
                .contains(savingAccount);
    }

    @Test
    void shouldThrowErrorWhenAddNullAccount() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> client.addAccount(null))
                .withMessage("Account must be not null");
    }

    @Test
    void shouldThrowErrorWhenAddAccountWithNotLinkedClient() {
        Client notLinkedClient = new Client(1, "1");
        SavingAccount savingAccount = new SavingAccount(1, notLinkedClient, 1);

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> client.addAccount(savingAccount));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowWhenNullAndEmptyName(String invalidName) {
        assertThrows(IllegalArgumentException.class, () -> new Client(100, invalidName));
    }

    @Test
    void shouldThrowWhenNegativeId() {
        assertThrows(IllegalArgumentException.class, () -> new Client(-100, VALID_CLIENT_NAME));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void shouldCreateClientWhenValidParams(int clientId) {
        Client client = new Client(clientId, VALID_CLIENT_NAME);

        Assertions.assertEquals(VALID_CLIENT_NAME, client.getName());
        Assertions.assertEquals(clientId, client.getId());
    }
}
