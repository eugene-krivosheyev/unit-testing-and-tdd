package com.acme.banking.dbo.domain;

import static java.util.Collections.unmodifiableCollection;

import java.util.Collection;

public class Branch {

  private Collection<Account> accounts; //TODO

  public Branch(Collection<Account> accounts) {
    this.accounts = accounts;
  }

  public Collection<Account> getAccounts() {
    return unmodifiableCollection(accounts);
  }

  public Collection<Branch> getChildren() {
    return null; //TODO
  }
}
