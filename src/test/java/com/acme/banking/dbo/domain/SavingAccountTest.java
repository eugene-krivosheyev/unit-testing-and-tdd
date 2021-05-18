package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {
  public static final int negativeId = -1;
  private final int dummyId = 1;
  private final Client dummyClient = new Client(123, "client name");
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

  private SavingAccount getTestSavingAccount() {

    return new SavingAccount(dummyId, dummyClient, dummyAmount);
  }
}