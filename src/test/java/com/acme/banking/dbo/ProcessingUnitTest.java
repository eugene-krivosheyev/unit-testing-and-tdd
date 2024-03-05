package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.CashInternalLogger;
import com.acme.banking.dbo.domain.CashTransaction;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.exception.OddTransactionValidationException;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.service.CashLoggerProvider;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProcessingUnitTest {

    @Test
    @DisplayName("Успешно передать день в другой аккаунт")
    void givenTwoAccountsAndTransferAmountBetweenThey() {

        var mockedRepository = mock(AccountRepository.class);
        var processing = new Processing(mockedRepository, new CashLoggerProvider());
        SavingAccount fromAccount = new SavingAccount(1, new Client(1, "aaa"), 1);
        SavingAccount toAccount = new SavingAccount(2, new Client(1, "bbb"), 0);
        when(mockedRepository.getAccount(1)).thenReturn(Optional.of(fromAccount));
        when(mockedRepository.getAccount(2)).thenReturn(Optional.of(toAccount));

        processing.transfer(1, 2, 1);

        assertAll(() -> assertEquals(0, fromAccount.getAmount()),
            () -> assertEquals(1, toAccount.getAmount())
        );
    }

    @Test
    @DisplayName("Успешно вывести деньги с аккаунта, проверка внутреннего логера")
    void givenProcessingServiceShouldProvideExtractionsFromAccount() {

        var mockedRepository = mock(AccountRepository.class);
        var processing = new Processing(mockedRepository, new CashLoggerProvider());
        SavingAccount fromAccount = new SavingAccount(1, new Client(1, "aaa"), 1);
        Integer toAccountId = null;
        when(mockedRepository.getAccount(1)).thenReturn(Optional.of(fromAccount));
        var cashTransactions = (List<CashTransaction>) ReflectionTestUtils.getField(new CashInternalLogger(),
            "cashTransactions");

        processing.transfer(1, toAccountId, 1);

        assertAll(() -> assertEquals(0, fromAccount.getAmount()),
            () -> assertEquals(1, cashTransactions.size()));
    }

    @Test
    @DisplayName("Ошибка валидации по сумме операции")
    void givenProcessingServiceShouldThrowExceptionByValidation() {

        var mockedRepository = mock(AccountRepository.class);
        var processing = new Processing(mockedRepository, new CashLoggerProvider());

        assertThrows(OddTransactionValidationException.class,
            () -> processing.transfer(1, null, 2)
        );
    }

    @Test
    @DisplayName("Успешно вывести деньги с аккаунта, проверка логгирования вывода")
    void givenProcessingServiceShouldProvideExtractionsFromAccountAndCaptureLog() {

        var mockedRepository = mock(AccountRepository.class);
        var cashLoggerProviderMock = mock(CashLoggerProvider.class);
        var processing = new Processing(mockedRepository, cashLoggerProviderMock);
        var doubleCaptor = ArgumentCaptor.forClass(Double.class);
        var intCaptor = ArgumentCaptor.forClass(Integer.class);
        SavingAccount fromAccount = new SavingAccount(1, new Client(1, "aaa"), 1);
        Integer toAccountId = null;
        when(mockedRepository.getAccount(1)).thenReturn(Optional.of(fromAccount));

        processing.transfer(1, toAccountId, 1);

        verify(cashLoggerProviderMock).log(doubleCaptor.capture(), intCaptor.capture());
        assertAll(() -> assertEquals(0, fromAccount.getAmount()),
            () -> assertEquals(1, intCaptor.getValue()),
            () -> assertEquals(1.0d, doubleCaptor.getValue()));
    }

}