package com.acme.banking.dbo.builder;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.ClientsRepository;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestClientRepository {

    private final ClientsRepository repository;
    private final UUID id;
    private final String name;

    private TestClientRepository(Builder builder) {
        repository = builder.repository;
        id = builder.id;
        name = builder.name;
    }

    public ClientsRepository getRepository() {
        return repository;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private ClientsRepository repository = mock(ClientsRepository.class);
        private String name;
        private UUID id;

        public Builder() {
            name = "Name";
            id = UUID.randomUUID();
        }

        public TestClientRepository build() {
            when(repository.create(name)).thenReturn(new Client(id, name));
            return new TestClientRepository(this);
        }
    }
}
