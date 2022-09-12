package com.acme.banking.dbo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProcessingTest {

    @InjectMocks
    private Processing processing;

    @Mock
    private Cash cashDouble;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ClientService clientService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AccountService accountService;


    @Test
    void createClient_whenAll_thenNewClientWithRequestName() {
        final String testName = "name";
        when(clientService.generateNewClient(eq(testName)).getName())
            .thenReturn(testName);

        Client actual = processing.createClient(testName);

        assertEquals(testName, actual.getName());
    }

    @Test
    void getAccountsByClientId_whenClientHasOneAccount_thenReturnAccountListWithOneValue() {
        processing.getAccountsByClientId(1);

        verify(accountService)
            .getAccountsByClientId(eq(1));
    }

    @Test
    void transfer_when_then() {

        processing.transfer(1,2, 10.);
    }

    @Test
    void cash_whenArgsValid_thenExecuteMethodLog() {
        double amount = 10;
        int id = 1;

        processing.cash(10., 1);

        verify(cashDouble).log(amount, id);
    }

    @Test
    void cash_whenArgsWrong_thenEThrowException() {
        assertThrows(IllegalArgumentException.class, () -> processing.cash(-10, 1));
    }
}