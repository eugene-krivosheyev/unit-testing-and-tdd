package com.acme.banking.dbo.dal;

import java.util.UUID;

public interface ClientRepository {
    UUID create(String name);
}
