package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;

import java.util.UUID;
import static org.mockito.Mockito.*;

public class MockitoClientBuilder {
    private UUID id = UUID.randomUUID();
    private String name = "default name";


    public MockitoClientBuilder withId(UUID id) {
        this.id = id;
        return this;

    }

    public MockitoClientBuilder withName() {
        this.name = name;
        return this;
    }

    public Client build() {
        Client stubClient = mock(Client.class);
        when(stubClient.getId()).thenReturn(this.id);
        when(stubClient.getName()).thenReturn(this.name);
        return stubClient;
    }
}
