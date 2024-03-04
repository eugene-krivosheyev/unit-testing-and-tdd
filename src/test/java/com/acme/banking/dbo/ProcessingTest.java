package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.AccountRepository;
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
        var mockedRepository = mock(AccountRepository.class);
        when(mockedRepository.getAccount(1)).thenReturn(new SavingAccount(1, new Client(1, "aoa"), 1));
        when(mockedRepository.getAccount(2)).thenReturn(new SavingAccount(2, new Client(1, "aoa"), 0));


    }


    @Test
    @DisplayName("Успешно получаем коллекцию аккаунтов по ID клиента")
    void creatingClient() {

    }
}