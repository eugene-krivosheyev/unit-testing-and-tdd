package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite for client")
public class ClientTest {
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

    @Test
    public void shouldNotAddSavingAccountWhenAccountIsInvalid() {
        int clientId = 1;
        Client sut = new Client(clientId, "dummy name");

        assertAll(
                "Adding invalid SaveAccount",
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> sut.addAccount(null)
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            Client invalidClient = new Client(2, "dummyName");
                            SavingAccount invalidAccount = new SavingAccount(1, invalidClient, 1);
                            sut.addAccount(invalidAccount);
                        }
                )
        );
    }

    @Test
    public void shouldAddSavingAccountWhenAllCorrect() {
        int clientId = 1;
        Client sut = new Client(clientId, "dummy name");
        SavingAccount validAccount = new SavingAccount(1, sut, 1);

        assertDoesNotThrow(
                () -> sut.addAccount(validAccount)
        );
    }

    @Test
    public void shouldCreateWithIdMoreThanZero() {
        int id = 1;
        String dummyName = "test name";
        Client sut = new Client(id, dummyName);

        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(id))
                ));

        assertDoesNotThrow(
                () -> new Client(id, dummyName)
        );
    }

    @Test
    public void shouldNotCreateWithNotPositiveIdAndWithEmptyOrNullName() {
        int dummyId = 1;
        String dummyName = "test name";

        assertAll(
                "",
                () -> {
                    int id = 0;
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> new Client(id, dummyName)
                    );
                },
                () -> {
                    int id = -1;
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> new Client(id, dummyName)
                    );
                },
                () -> {
                    String name = null;
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> new Client(dummyId, name)
                    );
                },
                () -> {
                    String name = "";
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> new Client(dummyId, name)
                    );
                }
        );
    }

    @Test
    public void shouldCreateWithNameIsNotNullAndEmpty() {
        int dummyId = 1;
        String name = "test name";

        assertDoesNotThrow(
                () -> new Client(dummyId, name)
        );
    }
}
