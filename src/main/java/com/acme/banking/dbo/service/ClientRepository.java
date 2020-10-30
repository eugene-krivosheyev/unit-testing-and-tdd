package com.acme.banking.dbo.service;

import java.util.UUID;

public interface ClientRepository {
    UUID createClient(String name);
}