package com.acme.banking.dbo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Client test cases")
public class ClientTest {

  @Test
  void shouldCreateClientWhenIdIsPositiveAndNameIsNotNullAndNotEmpty() {

    int clientId = 1;
    String clientName = "test client";

    Client sut = new Client(clientId, clientName);

    assertThat(sut.getId())
        .describedAs("Client id should be equal %s", clientId)
        .isEqualTo(clientId);

    assertThat(sut.getName())
        .describedAs("Client name should be equal %s", clientName)
        .isEqualTo(clientName);

  }

  @Test
  void shouldNotCreateClientAndShowErrorWhenIdIsNegative() {

    int clientId = -1;
    String clientName = "test client";

    assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));

  }

  @Test
  void shouldNotCreateClientAndShowErrorWhenNameIsNull() {

    int clientId = 1;
    String clientName = null;

    assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));

  }

  @Test
  void shouldNotCreateClientAndShowErrorWhenNameIsEmpty() {

    int clientId = 1;
    String clientName = "";

    assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));

  }

  @Test
  void shouldAddAccountToClientAccountsWhenAccountIsNonNull() {

    Client client = new Client(1, "test name");
    Account account = new SavingAccount(1, client, 10d);

    assumeThat(client.getAccounts().size()).isEqualTo(0);

    client.addAccount(account);

    assertThat(client.getAccounts().size())
        .isEqualTo(1);

  }

  @Test
  void shouldShowErrorWhenAddNullAccountToClient() {

    Client client = new Client(1, "test name");
    Account nullAccount = null;

    assumeThat(client.getAccounts().size()).isEqualTo(0);

    assertThrows(IllegalArgumentException.class, () -> client.addAccount(nullAccount));

  }

}
