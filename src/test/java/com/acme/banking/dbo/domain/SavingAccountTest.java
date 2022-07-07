package com.acme.banking.dbo.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.Test;

import static com.acme.banking.dbo.domain.ClientAccountTestHelper.DUMMY_AMOUNT;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.DUMMY_ID;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.DUMMY_NAME;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.createAccountForClient;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.createClient;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SavingAccountTest {

    @Test
    void shouldNotCreateSavingAccountWhenIdIsZero() {
        final int id = 0;

        assertThatThrownBy(() -> {
            new SavingAccount(id, createClient(), DUMMY_AMOUNT);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id is not positive");
    }

    @Test
    void shouldNotCreateSavingAccountWhenIdIsNegative() {
        final int id = -1;
        assertThatThrownBy(() -> {
            new SavingAccount(id, createClient(), DUMMY_AMOUNT);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id is not positive");
    }

    @Test
    void shouldNotCreateSavingAccountWhenClientIsNull() {
        Client client = null;

        assertThatThrownBy(() -> {
            new SavingAccount(DUMMY_ID, client, DUMMY_AMOUNT);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("client is null");
    }

    @Test
    void shouldNotCreateSavingAccountWhenAmountIsInvalid() {
        final double amount = -1D;

        assertThatThrownBy(() -> {
            new SavingAccount(DUMMY_ID, createClient(), amount);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("amount is negative");
    }

    @Test
    void shouldCreateSavingAccount() {
        Client dummyClient = createClient();
        SavingAccount savingAccount = createAccountForClient(dummyClient);

        Assertions.assertThat(savingAccount)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
        Assertions.assertThat(savingAccount.getId()).isEqualTo(DUMMY_ID);
        Assertions.assertThat(savingAccount.getAmount()).isEqualTo(DUMMY_AMOUNT);
        Assertions.assertThat(savingAccount.getClient())
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("name", DUMMY_NAME)
                .hasFieldOrPropertyWithValue("id", DUMMY_ID);
    }

    @Test
    void shouldAddAccountToClient() {

        Client client = createClient();
        SavingAccount account = createAccountForClient(client);
        Assumptions.assumeThat(client.getAccounts()).isEmpty();

        account.addAccountToClient();

        org.assertj.core.api.Assertions.assertThat(client.getAccounts())
                .isNotEmpty()
                .hasSize(1)
                .contains(account);
    }
}
