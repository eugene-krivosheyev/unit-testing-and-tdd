package com.acme.banking.dbo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test suite")
public class ClientTest {


  @Test
  void shouldCreateClientWhenIdIsPositiveAndNameIsNotNullAndNotEmpty() {

    int clientId = 1;
    String clientName = "test client";

    Client sut = new Client(clientId, clientName);

    Assertions.assertThat(sut.getId())
        .describedAs("Client id should be equal %s", clientId)
        .isEqualTo(clientId);

    Assertions.assertThat(sut.getName())
        .describedAs("Client name should be equal %s", clientName)
        .isEqualTo(clientName);

  }

  @Test
  void shouldNotCreateClientAndThrowExceptionWhenIdIsNegative() {

    int clientId = -1;
    String clientName = "test client";

    assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));

  }

  @Test
  void shouldNotCreateClientAndThrowExceptionWhenNameIsNull() {

    int clientId = 1;
    String clientName = null;

    assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));

  }

  @Test
  void shouldNotCreateClientAndThrowExceptionWhenNameIsEmpty() {

    int clientId = 1;
    String clientName = "";

    assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));

  }

  @Test
  void shouldAddAccountToClientAccountsWhenAccountIsNonNull() {

    Client client = new Client(1, "test name");
    Account account = new SavingAccount(1, client, 10d);

    Assumptions.assumeThat(client.getAccounts().size()).isEqualTo(0);

    client.addAccount(account);

    Assertions.assertThat(client.getAccounts().size())
        .isEqualTo(1);

  }

  @Test
  @Disabled
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

}
