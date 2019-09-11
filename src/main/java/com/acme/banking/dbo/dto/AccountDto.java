package com.acme.banking.dbo.dto;

import java.util.UUID;

public class AccountDto {
    private UUID id;

    public AccountDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
