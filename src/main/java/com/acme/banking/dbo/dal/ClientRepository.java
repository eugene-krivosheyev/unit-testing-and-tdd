package com.acme.banking.dbo.dal;

import com.acme.banking.dbo.domain.Client;

import java.util.UUID;

public interface ClientRepository {
    UUID create(String name);
    Client findById(UUID id);
}
