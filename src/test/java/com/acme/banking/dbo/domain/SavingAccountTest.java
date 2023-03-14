package com.acme.banking.dbo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SavingAccountTest {


  @Test
  void shouldCreateSavingAccountWhenAccountIdAndAmountArePositiveAndClientIsValid() {

    String clientName = "dummy client name";

    int clientId = 1;
    int accountId = 1;
    double accountAmount = 10d;

    Client client = new Client(clientId, clientName);
    Account account = new SavingAccount(accountId, client, accountAmount);

    assertThat(account.getId()).isEqualTo(clientId);
    assertThat(account.getAmount()).isEqualTo(accountAmount);
    assertThat(account.getClient()).isEqualTo(client);

  }

  @Test
  void shouldShowArgumentErrorWhenAccountIdIsNegative() {

    int accountId = -1;
    double accountAmount = 10d;
    Client client = new Client(1, "dummy client name");

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new SavingAccount(accountId, client, accountAmount));

  }

  @Test
  void shouldShowArgumentErrorWhenAccountAmountIsNegative() {

    int accountId = 1;
    double accountAmount = -1d;
    Client client = new Client(1, "dummy client name");

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new SavingAccount(accountId, client, accountAmount));

  }

  @Test
  void shouldShowArgumentErrorWhenClientIsNull() {

    int accountId = 1;
    double accountAmount = 10d;

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new SavingAccount(accountId, null, accountAmount));

  }


}