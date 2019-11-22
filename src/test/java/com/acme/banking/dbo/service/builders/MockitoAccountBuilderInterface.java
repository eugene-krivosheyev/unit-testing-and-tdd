package com.acme.banking.dbo.service.builders;

import com.acme.banking.dbo.domain.Account;

public interface MockitoAccountBuilderInterface {
    MockitoAccountBuilderInterface withAccount(Account stubAccount);

}
