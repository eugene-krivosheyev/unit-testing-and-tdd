package com.acme.banking.dbo.builders;

import com.acme.banking.dbo.dao.ClientNotFoundException;
import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoClientRepositoryBuilder {
    private Map<UUID, Client> uuidClientMap = new HashMap<>();

    public MockitoClientRepositoryBuilder withClientById(UUID id, Client client) {
        uuidClientMap.put(id, client);
        return  this;
    }

    public ClientRepository build() throws ClientNotFoundException {
        ClientRepository clientRepoMock = mock(ClientRepository.class);
        for (Map.Entry<UUID, Client> entry : uuidClientMap.entrySet()) {
            when(clientRepoMock.findById(entry.getKey())).thenReturn(entry.getValue());
        }
        return clientRepoMock;
    }
}
