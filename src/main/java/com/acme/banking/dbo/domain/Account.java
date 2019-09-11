package com.acme.banking.dbo.domain;

import java.util.UUID;

public interface Account {
    UUID getId();
    UUID getClientId();
    void removeClient();
    void setClient(Client client);
}
