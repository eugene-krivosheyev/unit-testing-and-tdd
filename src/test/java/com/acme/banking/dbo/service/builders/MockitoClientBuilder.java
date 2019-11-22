package com.acme.banking.dbo.service.builders;

import com.acme.banking.dbo.domain.Client;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoClientBuilder {
    private UUID id = UUID.randomUUID();
    private String name = "default name";
    private Client stubClient = mock(Client.class);


    public MockitoClientBuilder withId(UUID id) {
        this.id = id;
        return this;

    }

    public MockitoClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Client build() {
        when(stubClient.getId()).thenReturn(this.id);
        when(stubClient.getName()).thenReturn(this.name);
        return stubClient;
    }
}
