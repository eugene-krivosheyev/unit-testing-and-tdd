package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {
  private static final int negativeId = -1;
  private final int dummyId = 1;
  private final Client dummyClient = new Client(123, "client client");
  private final double dummyAmount = 1.23;

  @Test
  public void shouldNotBeCreatedWhenNegativeId() {
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
                    new SavingAccount(negativeId, dummyClient, dummyAmount));
    assertEquals(thrown.getMessage(), "id!");
  }

  @Test
  public void shouldNotBeCreatedWhenNullClient() {
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
                    new SavingAccount(dummyId, null, dummyAmount));

    assertEquals(thrown.getMessage(), "Client!");
  }

  @Test
  public void getAmount() {
    SavingAccount sut = getTestSavingAccount();
    assertEquals(dummyAmount, sut.getAmount());
  }

  @Test
  public void getId() {
    SavingAccount sut = getTestSavingAccount();
    assertEquals(dummyId, sut.getId());
  }

  @Test
  public void getClient() {
    SavingAccount sut = getTestSavingAccount();
    assertSame(dummyClient, sut.getClient()); // Same or Equals
  }


  // humcrest

  @Test
  public void shouldSetAllPropertiesonConstruction() {
    SavingAccount sut = getTestSavingAccount();

    assertThat(sut,
            allOf(
                    hasProperty("id", notNullValue()),
                    hasProperty("id", equalTo(dummyId)),
                    hasProperty("amount", is(dummyAmount))
            ));

  }

  // Parameterized
  @ParameterizedTest
  @MethodSource("paramsProvider")
  public void shouldFailOnConstructorIncorrectAgrs(SavingAccountTestParams testParams){
    final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
            () -> new SavingAccount(testParams.getId(), testParams.getClient(), testParams.getAmount()));
    assertEquals(testParams.getExceptionMessage(), thrown.getMessage() );
  }

  static Stream<SavingAccountTestParams> paramsProvider(){

    final Client dummyClient = new Client(123, "client client");

    return Stream.of(
      new SavingAccountTestParams(-1, dummyClient, 1.1, "id!"),
      new SavingAccountTestParams(1, null, 1.1, "Client!"),
      new SavingAccountTestParams(1, dummyClient, -1, "amount!")
    );
  }


  private SavingAccount getTestSavingAccount() {
    return new SavingAccount(dummyId, dummyClient, dummyAmount);
  }
}


class SavingAccountTestParams {
  public int getId() {
    return id;
  }

  public Client getClient() {
    return client;
  }

  public double getAmount() {
    return amount;
  }

  public String getExceptionMessage() {
    return exceptionMessage;
  }

  private int id;
  private Client client;
  private double amount;
  private String exceptionMessage;

  public SavingAccountTestParams(int id, Client client, double amount, String exceptionMessage) {
    this.id = id;
    this.client = client;
    this.amount = amount;
    this.exceptionMessage = exceptionMessage;
  }


}