package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class ClientTest {
  final String testName = "client name";
  final int testId = 123;

  @Test
  public void shouldNotBeCreatedWhenNegativeId(){
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
      new Client(-1, "clinentName"));
    assertEquals(thrown.getMessage(), "id!");
  }

  @Test
  public void shouldNotBeCreatedWhenNameNotProvided(){
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
                    new Client(1, null));

    assertEquals(thrown.getMessage(), "name!");


    thrown = assertThrows(IllegalArgumentException.class, () ->
                    new Client(1, ""));

    assertEquals(thrown.getMessage(), "name is empty!");
  }

  @Test
  public void getId() {
    Client sut = getTestClient();
    assertEquals(testId, sut.getId());
  }

  @Test
  public void getName() {
    Client sut = getTestClient();
    assertEquals(testName, sut.getName());
  }

  private Client getTestClient() {
    Client sut = new Client(testId, testName);

    return sut;
  }
}