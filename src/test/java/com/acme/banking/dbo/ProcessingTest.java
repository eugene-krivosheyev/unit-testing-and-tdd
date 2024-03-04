package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessingTest {

    private final Processing processing;

    public ProcessingTest(Processing processing) {
        this.processing = processing;
    }

    @Test
    @DisplayName("Успешно создаём клиента в Processing")
    void successCreateNewClient() {
        var stubElement = mock(ClientRepository.class);
        when(stubElement.createUniqueId())
                .thenReturn(11);

        Client newClient = processing.createClient("НовыйКлиент");
        Assertions.assertAll(
                () -> Assertions.assertEquals(11, newClient.getId()),
                () -> Assertions.assertEquals("НовыйКлиент", newClient.getName()));
    }


    @Test
    @DisplayName("Успешно получаем коллекцию аккаунтов по ID клиента")
    void creatingClient() {

    }
}