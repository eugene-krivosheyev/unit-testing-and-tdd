package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Saving account test suite")
public class SavingAccountTest {
    @Test
    @DisplayName("Saving account successfully created")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        final int savingAccountId = 1;
        final double amount = 5;
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);

        SavingAccount savingAccount = new SavingAccount(savingAccountId, sut, amount);
        assumeTrue(savingAccount != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Saving Accounts store its properties",
                () -> assertEquals(savingAccountId, savingAccount.getId()),
                () -> assertEquals(sut, savingAccount.getClient()),
                () -> assertEquals(amount, savingAccount.getAmount())
        );

        //Hamcrest:
        assertThat(savingAccount,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(savingAccountId)),
                        hasProperty("client", equalTo(sut)),
                        hasProperty("amount", is(amount))

                ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(savingAccount)
                .hasFieldOrPropertyWithValue("id", savingAccountId)
                .hasFieldOrPropertyWithValue("client", sut)
                .hasFieldOrPropertyWithValue("amount", amount);
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }
}
