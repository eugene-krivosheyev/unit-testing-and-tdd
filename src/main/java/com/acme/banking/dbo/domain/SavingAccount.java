package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {

  private int id;
  private Client client;
  private double amount;

  public SavingAccount(int id, Client client, double amount) {
    if (id < 0) {
      throw new IllegalArgumentException(String.format("Saving account id is not valid. %d", id));
    }
    if (amount < 0d) {
      throw new IllegalArgumentException(
          String.format("Saving account amount is not valid. %d", id));
    }
    if (client == null) {
      throw new IllegalArgumentException("Saving account client is undefined");
    }

    this.id = id;
    this.client = client;
    this.amount = amount;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public Client getClient() {
    return client;
  }
}
