package com.acme.banking.dbo.domain;

import java.util.UUID;

public interface Account {
    UUID getId();
    UUID getClientId();
    double getAmount();
    void removeClient();
    void setClient(Client client);
}
