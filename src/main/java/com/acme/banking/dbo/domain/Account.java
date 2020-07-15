package com.acme.banking.dbo.domain;

import java.util.UUID;

public interface Account {
    int getId();
    int getClientId(); //TODO reference integrity
}
