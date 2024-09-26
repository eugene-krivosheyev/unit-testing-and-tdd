package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {
    Client saveClientWithName(String name);
}
