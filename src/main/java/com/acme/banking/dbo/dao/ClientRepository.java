package com.acme.banking.dbo.dao;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {
    Client add(String name);
}
