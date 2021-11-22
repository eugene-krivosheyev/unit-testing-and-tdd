package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Test suite")
public class SavingAccountTest {

    @ParameterizedTest
    @Disabled
    @ValueSource(doubles = { 0, 1.1, 2.4, 3000000.50, 10/3 })
    public void shouldStorePropertiesWhenCreated(double amount) {
        //region given
        final int accountId = 0;
        //final double amount = 5.5;
        final Client client = new Client(1, "clientName");
        //endregion

        //region when
        Account sut = new SavingAccount(accountId, client, amount);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Hamcrest:
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(accountId)),
                        hasProperty("client", notNullValue()),
                        hasProperty("client", sameInstance(client)),
                        hasProperty("amount", equalTo(amount))
                        //sut.getClient().checkAccountValid(sut)
                ));
        //endregion
    }

    @Test
    public void shouldClientHadThisAccountWhenCreated() {
        //region given
        final int accountId = 0;
        final double amount = 5.5;
        final Client client = new Client(1, "clientName");
        //endregion

        //region when
        Account sut = new SavingAccount(accountId, client, amount);
        assumeTrue(sut != null);
        //endregion


        //region then
        ///
        ///         Client has this account sut.getClient()
        ///
        //Junit5:

        // Logic AND - all checks must pass
//        assertAll("Client store its properties",
//                () -> assertEquals(clientId, sut.getId()),
//                () -> assertEquals(clientName, sut.getName())
//        );

        assertTrue(client.checkAccountIsOwnedBy(sut));
        //endregion
    }

    @Test
    public void shouldNotCreateWhenAmountIsNegative() {
        // region 1: Given
        final int id = 0;
        final int clientId = 0;
        final double amount = -1.0;
        final Client client = new Client(clientId, "Name");
        // end region

        // region 2: When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(id, client, amount)
        );
        // end region

        // region 3: Then
        //assertThrows(IllegalArgumentException.class,  ()->new SavingAccount(id, client, amount) );

        assertTrue(thrown.getMessage().contains("Amount should not be negative"));

        // end region
    }

    @Test
    public void shouldThrowWhenNameIsNull() {
        // region 1: Given
        final int id = 0;
        final double amount = 0.0;
        final Client client = null;
        // end region

        // region 2: When

        // end region

        // region 3: Then
        assertThrows(IllegalArgumentException.class,  ()->new SavingAccount(id, client, amount) );
        // end region
    }

    @Test
    public void shouldNotThrowWhenNameValid() {
        // region 1: Given
        final int id = 0;
        final double amount = 0.0;
        final Client client = new Client(id, "Name");
        // end region

        // region 2: When

        // end region

        // region 3: Then
        assertDoesNotThrow(()->new SavingAccount(id, client, amount));
        // end region
    }
}
