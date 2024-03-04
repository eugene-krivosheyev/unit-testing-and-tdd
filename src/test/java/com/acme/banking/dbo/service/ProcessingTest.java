package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.CashLogService;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProcessingTest {

    private ClientRepository clientRepositoryMock;
    private AccountRepository accountRepositoryMock;
    private CashLogService cashLogServiceMock;
    private Processing processingService;

    @BeforeEach
    public void setUp() {
        clientRepositoryMock = mock(ClientRepository.class);
        accountRepositoryMock = mock(AccountRepository.class);
        cashLogServiceMock = mock(CashLogService.class);
        processingService = new Processing(clientRepositoryMock, accountRepositoryMock, cashLogServiceMock);
    }

    @Test
    void shouldCreateClientWithValidName() {
        var expectedClient = new Client(1, "Ivan");

        when(clientRepositoryMock.saveClientWithName("Ivan")).thenReturn(new Client(1, "Ivan"));
        var actualClient = processingService.createClient("Ivan");

        verify(clientRepositoryMock, times(1)).saveClientWithName("Ivan");

        assertAll(
                () -> assertEquals(expectedClient.getId(), actualClient.getId()),
                () -> assertEquals(expectedClient.getName(), actualClient.getName()),
                () -> assertEquals(expectedClient.getAccounts().size(), actualClient.getAccounts().size())
        );
    }

    @Test
    void shouldNotCreateClientWithInvalidName() {
        String invalidNullName = null;
        var invalidEmptyName = " ";

        assertThrows(IllegalArgumentException.class, () -> processingService.createClient(invalidNullName));
        assertThrows(IllegalArgumentException.class, () -> processingService.createClient(invalidEmptyName));

        verify(clientRepositoryMock, times(0)).saveClientWithName(null);
        verify(clientRepositoryMock, times(0)).saveClientWithName("");
    }

    @Test
    void shouldReturnAllAccountsByValidClient() {
        var validClient = new Client(0, "Ivan");
        var clientAccount_1 = new SavingAccount(1, validClient, 10.00);
        var clientAccount_2 = new SavingAccount(2, validClient, 100.00);
        var validAccounts = Arrays.asList(clientAccount_1, clientAccount_2);
        validClient.addAccounts(validAccounts);

        when(accountRepositoryMock.findAccountsByClient(0)).thenReturn(Arrays.asList(clientAccount_1, clientAccount_2));

        var actualAccounts = processingService.getAccountsByClientId(validClient.getId());

        verify(accountRepositoryMock, times(1)).findAccountsByClient(0);

        assertAll(
                () -> assertTrue(Objects.nonNull(actualAccounts)),
                () -> assertEquals(validAccounts.size(), actualAccounts.size()),
                () -> assertEquals(validAccounts, actualAccounts)
        );
    }

    @Test
    void shouldTransferAmountBetweenTwoValidAccounts() {
        var validClientFrom = new Client(1, "Ivan");
        var validClientTo = new Client(2, "Sergey");
        var validAccountFrom = new SavingAccount(1, validClientFrom, 1000.00);
        var validAccountTo = new SavingAccount(2, validClientTo, 100.00);
        var transferedAccountFrom = new SavingAccount(1, validClientFrom, 900);
        var transferedAccountTo = new SavingAccount(2, validClientTo, 200);
        validClientFrom.addAccount(validAccountFrom);
        validClientTo.addAccount(validAccountTo);

        when(accountRepositoryMock.findAccountById(1)).thenReturn(validAccountFrom);
        when(accountRepositoryMock.findAccountById(2)).thenReturn(validAccountTo);
        when(accountRepositoryMock.updateClientAccount(transferedAccountFrom)).thenReturn(transferedAccountFrom);
        when(accountRepositoryMock.updateClientAccount(transferedAccountTo)).thenReturn(transferedAccountTo);

        processingService.transfer(1, 2, 100.00);

        verify(accountRepositoryMock, times(1)).findAccountById(1);
        verify(accountRepositoryMock, times(1)).findAccountById(2);
        verify(accountRepositoryMock, times(1)).updateClientAccount(transferedAccountFrom);
        verify(accountRepositoryMock, times(1)).updateClientAccount(transferedAccountTo);
    }

    @Test
    void shouldNotTransferNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> processingService.transfer(1, 2, -1));
    }

    @Test
    void shouldNotTransferWhenAccountFromNotFound() {
        when(accountRepositoryMock.findAccountById(1)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> processingService.transfer(1, 2, 100));
    }

    @Test
    void shouldNotTransferWhenAccountToNotFound() {
        when(accountRepositoryMock.findAccountById(0)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> processingService.transfer(1, -1, 100));
    }

    @Test
    void shouldNotTransferWhenClientFromAmountNotChanged() {
        var validClientFrom = new Client(1, "Ivan");
        var validClientTo = new Client(2, "Sergey");
        var validAccountFrom = new SavingAccount(1, validClientFrom, 1000.00);
        var validAccountTo = new SavingAccount(2, validClientTo, 100.00);
        var validTransferedAccountFrom = new SavingAccount(1, validClientFrom, 900);
        var invalidTransferedAccountFrom = new SavingAccount(1, validClientFrom, 1000);
        var validTransferedAccountTo = new SavingAccount(2, validClientTo, 200);

        when(accountRepositoryMock.findAccountById(1)).thenReturn(validAccountFrom);
        when(accountRepositoryMock.findAccountById(2)).thenReturn(validAccountTo);
        when(accountRepositoryMock.updateClientAccount(validTransferedAccountFrom)).thenReturn(invalidTransferedAccountFrom);
        when(accountRepositoryMock.updateClientAccount(validTransferedAccountTo)).thenReturn(validTransferedAccountTo);

        assertThrows(IllegalStateException.class, () -> processingService.transfer(1,2,100));

        verify(accountRepositoryMock, times(1)).findAccountById(1);
        verify(accountRepositoryMock, times(1)).findAccountById(2);
        verify(accountRepositoryMock, times(1)).updateClientAccount(validTransferedAccountFrom);
        verify(accountRepositoryMock, times(0)).updateClientAccount(validTransferedAccountTo);
    }

    @Test
    void shouldNotTransferWhenClientToAmountNotChanged() {
        var validClientFrom = new Client(1, "Ivan");
        var validClientTo = new Client(2, "Sergey");
        var validAccountFrom = new SavingAccount(1, validClientFrom, 1000.00);
        var validAccountTo = new SavingAccount(2, validClientTo, 100.00);
        var validTransferedAccountFrom = new SavingAccount(1, validClientFrom, 900);
        var validTransferedAccountTo = new SavingAccount(2, validClientTo, 200);
        var invalidTransferedAccountTo = new SavingAccount(2, validClientFrom, 100);

        when(accountRepositoryMock.findAccountById(1)).thenReturn(validAccountFrom);
        when(accountRepositoryMock.findAccountById(2)).thenReturn(validAccountTo);
        when(accountRepositoryMock.updateClientAccount(validTransferedAccountFrom)).thenReturn(validTransferedAccountFrom);
        when(accountRepositoryMock.updateClientAccount(validTransferedAccountTo)).thenReturn(invalidTransferedAccountTo);

        assertThrows(IllegalStateException.class, () -> processingService.transfer(1,2,100));

        verify(accountRepositoryMock, times(1)).findAccountById(1);
        verify(accountRepositoryMock, times(1)).findAccountById(2);
        verify(accountRepositoryMock, times(1)).updateClientAccount(validTransferedAccountFrom);
        verify(accountRepositoryMock, times(1)).updateClientAccount(validTransferedAccountTo);
    }

    @Test
    void shouldLogValidCashOperation() {
        var logAmount = 100.00;
        var logAccountId = 1;

        processingService.logCash(logAmount, logAccountId);

        verify(cashLogServiceMock, times(1)).logCash(100.00, 1);
    }

    @Test
    void shouldNotLogCashOperationWithInvalidAccountId() {
        var logAmount = 100.00;
        var negativeAmountId = -1;
        var zeroAmountId = 0;

        assertThrows(IllegalArgumentException.class, () -> processingService.logCash(logAmount, negativeAmountId));
        assertDoesNotThrow(() -> processingService.logCash(logAmount, zeroAmountId));

        verify(cashLogServiceMock, times(0)).logCash(100.00, negativeAmountId);
        verify(cashLogServiceMock, times(1)).logCash(100.00, zeroAmountId);
    }
}