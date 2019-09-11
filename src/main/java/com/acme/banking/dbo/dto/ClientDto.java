package com.acme.banking.dbo.dto;

import java.util.UUID;

public class ClientDto {
    private UUID id;
    private String name;

    public ClientDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
