package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Client;

public interface ClientsRepository {
    Client create(String name);
}
