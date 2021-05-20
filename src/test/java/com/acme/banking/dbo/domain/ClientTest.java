package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
  public static final int negativeId = -1;
  final String dummyName = "client name";
  final int dummyId = 123;

  @Test
  public void shouldNotBeCreatedWhenNegativeId(){
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
      new Client(negativeId, dummyName));
    assertEquals(thrown.getMessage(), "id!");
  }

  @Test
  public void shouldNotBeCreatedWhenNameNotProvided(){
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () ->
                    new Client(dummyId, null));

    assertEquals(thrown.getMessage(), "name!");


    thrown = assertThrows(IllegalArgumentException.class, () ->
                    new Client(dummyId, ""));

    assertEquals(thrown.getMessage(), "name is empty!");
  }



  @Test
  public void getId() {
    Client sut = getTestClient();
    assertEquals(dummyId, sut.getId());
  }

  @Test
  public void getName() {
    Client sut = getTestClient();
    assertEquals(dummyName, sut.getName());
  }

  private Client getTestClient() {
    return new Client(dummyId, dummyName);
  }
}