package com.acme.banking.dbo.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Create saving account")
class SavingAccountTest {


  @Test
  void shouldCreateSavingAccountWhenAccountIdAndAmountNonNegativeAndClientIsValid() {

    String dummyClientName = "dummy client name";

    int dummyClientId = 1;
    int dummyAccountId = 1;
    double dummyAccountAmount = 0.0d;

    Client dummyClient = new Client(dummyClientId, dummyClientName);
    Account sut = new SavingAccount(dummyAccountId, dummyClient, dummyAccountAmount);

    assertThat(sut)
        .hasFieldOrPropertyWithValue("id", dummyClientId)
        .hasFieldOrPropertyWithValue("client", dummyClient)
        .hasFieldOrPropertyWithValue("amount", dummyAccountAmount);

  }

  @Test
  void shouldShowArgumentErrorWhenAccountIdIsZero() {

    int dummyAccountId = 0;
    double dummyAccountAmount = 0d;
    Client dummyClient = new Client(1, "dummy client name");

    assertThrows(IllegalArgumentException.class,
        () -> new SavingAccount(dummyAccountId, dummyClient, dummyAccountAmount));

  }

  @Test
  void shouldShowArgumentErrorWhenAccountAmountIsNegative() {

    int dummyAccountId = 1;
    double dummyAccountAmount = -1d;
    Client dummyClient = new Client(1, "dummy client name");

    assertThrows(IllegalArgumentException.class,
        () -> new SavingAccount(dummyAccountId, dummyClient, dummyAccountAmount));

  }

  @Test
  void shouldShowArgumentErrorWhenClientIsNull() {

    int dummyAccountId = 1;
    double dummyAccountAmount = 1d;

    assertThrows(IllegalArgumentException.class,
        () -> new SavingAccount(dummyAccountId, null, dummyAccountAmount));

  }


}