package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
  final String clientValidName = "client name";
  final int clientValidId = 123;

  @Test
  public void shouldNotBeCreatedWhenNegativeId(){
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
      new Client(-1, clientValidName));
    assertEquals(thrown.getMessage(), "id!");
  }

  @Test
  public void shouldNotBeCreatedWhenNameNotProvided(){
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
                    new Client(clientValidId, null));

    assertEquals(thrown.getMessage(), "name!");


    thrown = assertThrows(IllegalArgumentException.class, () ->
                    new Client(clientValidId, ""));

    assertEquals(thrown.getMessage(), "name is empty!");
  }

  @Test
  public void getId() {
    Client sut = getTestClient();
    assertEquals(clientValidId, sut.getId());
  }

  @Test
  public void getName() {
    Client sut = getTestClient();
    assertEquals(clientValidName, sut.getName());
  }

  private Client getTestClient() {
    Client sut = new Client(clientValidId, clientValidName);

    return sut;
  }
}