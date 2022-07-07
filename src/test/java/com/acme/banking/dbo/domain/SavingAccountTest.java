package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.domain.Client;
import com.acme.banking.dbo.domain.domain.SavingAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.acme.banking.dbo.domain.ClientAccountTestHelper.DUMMY_AMOUNT;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.DUMMY_ID;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.createAccountForClient;
import static com.acme.banking.dbo.domain.ClientAccountTestHelper.createClient;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class SavingAccountTest {

    private final Client mockClient = mock(Client.class);

    @Test
    void shouldNotCreateSavingAccountWhenIdIsZero() {
        final int id = 0;

        assertThatThrownBy(() -> {
            new SavingAccount(id, mockClient, DUMMY_AMOUNT);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id is not positive");
    }

    @Test
    void shouldNotCreateSavingAccountWhenIdIsNegative() {
        final int id = -1;

        assertThatThrownBy(() -> {
            new SavingAccount(id, mockClient, DUMMY_AMOUNT);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id is not positive");
    }

    @Test
    void shouldNotCreateSavingAccountWhenClientIsNull() {
        assertThatThrownBy(() -> {
            new SavingAccount(DUMMY_ID, null, DUMMY_AMOUNT);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("client is null");
    }

    @Test
    void shouldNotCreateSavingAccountWhenAmountIsInvalid() {
        final double amount = -1D;

        assertThatThrownBy(() -> {
            new SavingAccount(DUMMY_ID, mockClient, amount);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("amount is negative");
    }

    @Test
    void shouldCreateSavingAccount() {
        SavingAccount savingAccount = createAccountForClient(mockClient);

        Assertions.assertThat(savingAccount)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void shouldAddAccountToClient() {
        Client client = createClient();
        SavingAccount account = createAccountForClient(client);

        org.assertj.core.api.Assertions.assertThat(client.getAccounts())
                .isNotEmpty()
                .hasSize(1)
                .contains(account);

        org.assertj.core.api.Assertions.assertThat(account.getClient()).isEqualTo(client);
    }
}
