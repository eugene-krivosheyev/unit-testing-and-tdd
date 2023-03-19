package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Processing - client test ")
class ProcessingClientUnitTest {

    @InjectMocks
    Processing sut;

    @Mock
    ClientRepository clientRepository;

    @Test
    void shouldCreateNewClientWhenCreationBasedOnDummyClient() {

        Client dummyClient = mock(Client.class);
        Client stubClient = mock(Client.class);
        when(stubClient.getId()).thenReturn(1);
        when(clientRepository.save(dummyClient)).thenReturn(stubClient);

        Client createdClient = sut.createClient(dummyClient);

        assertThat(createdClient)
                .describedAs("New client id should be equal 1")
                .hasFieldOrPropertyWithValue("id", 1);

    }

}