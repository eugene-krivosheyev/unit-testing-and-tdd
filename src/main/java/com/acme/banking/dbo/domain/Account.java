package com.acme.banking.dbo.domain;

import java.util.UUID;

public interface Account {
    UUID getId();
    UUID getClientId();
    double getAmount();
    void setClient(Client client);
    String getClientName();
}
