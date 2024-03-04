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
    @DisplayName("Успешно передать день в другой аккаунт")
    void givenTwoAccountsAndTransferAmountBetweenThey() {

        var mockedRepository = mock(AccountRepository.class);
        SavingAccount fromAccount = new SavingAccount(1, new Client(1, "aaa"), 1);
        SavingAccount toAccount = new SavingAccount(2, new Client(1, "bbb"), 0);
        when(mockedRepository.getAccount(1)).thenReturn(fromAccount);
        when(mockedRepository.getAccount(2)).thenReturn(toAccount);

        processing.transfer(1, 2, 1);

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, fromAccount.getAmount()),
                () -> Assertions.assertEquals(1, toAccount.getAmount()));
    }

    @Test
    @DisplayName("Успешно вывести деньги с аккаунта")
    void successTransfer() {

        var mockedRepository = mock(AccountRepository.class);
        SavingAccount fromAccount = new SavingAccount(1, new Client(1, "aaa"), 1);
        Integer toAccountId = null;
        when(mockedRepository.getAccount(1)).thenReturn(fromAccount);

        processing.transfer(1, toAccountId, 1);

//        Assertions.assertAll(
//                () -> Assertions.assertEquals()),
    }
}