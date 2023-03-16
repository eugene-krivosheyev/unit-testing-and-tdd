package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Create client test cases")
class ClientTest {

    @Test
    void shouldCreateClientWhenIdIsPositiveAndNameIsNotNullAndNotEmpty() {

        int dummyClientId = 1;
        String dummyClientName = "dummy test client";

        Client sut = new Client(dummyClientId, dummyClientName);

        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", dummyClientId)
                .describedAs("Client id should be equal %s", dummyClientId)
                .hasFieldOrPropertyWithValue("name", dummyClientName)
                .describedAs("Client name should be equal %s", dummyClientName);

    }

    @Test
    void shouldNotCreateClientAndShowErrorWhenIdIsNegative() {

        int dummyClientId = -1;
        String dummyClientName = "dummy test client";

        assertThrows(IllegalArgumentException.class, () -> new Client(dummyClientId, dummyClientName));

    }

    @Test
    void shouldNotCreateClientAndShowErrorWhenNameIsNull() {

        int clientId = 1;
        String clientName = null;

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));

    }

    @Test
    void shouldNotCreateClientAndShowErrorWhenNameIsEmpty() {

        int dummyClientId = 1;
        String dummyClientName = "";

        assertThrows(IllegalArgumentException.class, () -> new Client(dummyClientId, dummyClientName));

    }

    @Test
    void shouldAddAccountToClientAccountsWhenAccountIsNonNull() {

        Client sut = new Client(1, "dummy test name");
        Account dummyAccount = new SavingAccount(1, sut, 10d);

        assumeThat(sut.getAccounts())
                .describedAs("Account collection should be equal 0 before adding new one")
                .hasSize(0);

        sut.addAccount(dummyAccount);

        assertThat(sut.getAccounts())
                .describedAs("Account collection should be equal 1 before adding new one")
                .hasSize(1);

    }

    @Test
    void shouldShowErrorWhenAddNullAccountToClient() {

        Client sut = new Client(1, "dummy test name");
        Account nullAccount = null;

        assumeThat(sut.getAccounts())
                .describedAs("Account collection should be equal 0 before adding new one")
                .hasSize(0);

        assertThrows(IllegalArgumentException.class, () -> sut.addAccount(nullAccount));

    }

    @Test
    void shouldShowErrorWhenAddAccountWithDifferentClient() {

        Client sut = new Client(1, "dummy test name");

        Client dummyDifferentClient = new Client(2, "different dummy test name");

        Account dummyAccount = new SavingAccount(1, dummyDifferentClient, 1d);

        assumeThat(sut.getAccounts())
                .describedAs("Account collection should be equal 0 before adding new one")
                .hasSize(0);

        assertThrows(IllegalArgumentException.class, () -> sut.addAccount(dummyAccount));

    }

}
