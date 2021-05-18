package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {
  private final int testValidId = 1;
  private final Client testClient = new Client(123, "client name");
  private final double testValidAmount = 1.23;

  @Test
  public void shouldNotBeCreatedWhenNegativeId() {
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
                    new SavingAccount(-1, testClient, testValidAmount));
    assertEquals(thrown.getMessage(), "id!");
  }

  @Test
  public void shouldNotBeCreatedWhenNullClient() {
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
                    new SavingAccount(testValidId, null, testValidAmount));

    assertEquals(thrown.getMessage(), "Client!");
  }

  @Test
  public void getAmount() {
    SavingAccount sut = getTestSavingAccount();
    assertEquals(testValidAmount, sut.getAmount());
  }

  @Test
  public void getId() {
    SavingAccount sut = getTestSavingAccount();
    assertEquals(testValidId, sut.getId());
  }

  @Test
  public void getClient() {
    SavingAccount sut = getTestSavingAccount();
    assertSame(testClient, sut.getClient()); // Same or Equals
  }

  private SavingAccount getTestSavingAccount() {
    SavingAccount sa = new SavingAccount(testValidId, testClient, testValidAmount);

    return sa;
  }
}