package com.acme.banking.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ProcessingTest {

    @Test
    void transferShouldSaveNewAccValueTwoTimes() {
        AccountRepository mockRepo = mock(AccountRepository.class);
        Processing sut = new Processing(mockRepo);

        Account mockFromAcc = mock(Account.class);
        Account mockToAcc = mock(Account.class);

        when(mockRepo.findById(1)).thenReturn(mockFromAcc);
        when(mockRepo.findById(2)).thenReturn(mockToAcc);

        sut.transfer(1, 2, 100); // cmd + p to show method signature
        verify(mockRepo, times(1)).save(mockFromAcc);
        verify(mockRepo, times(1)).save(mockToAcc);
    }


    @Test
    void checkAmountAfterTransfer() { // state based test
        AccountRepository mockRepo = mock(AccountRepository.class);
        Client stub = mock(Client.class);

        SavingAccount fromAcc = new SavingAccount(1 , stub, 100);
        SavingAccount toAcc = new SavingAccount(2 , stub, 200);

        when(mockRepo.findById(1)).thenReturn(fromAcc);
        when(mockRepo.findById(2)).thenReturn(toAcc);

        Processing sut = new Processing(mockRepo);

        sut.transfer(1, 2, 100);

        assertAll(
                () -> assertEquals(0, fromAcc.getAmount()),
                () -> assertEquals(300, toAcc.getAmount())
        );

    }


}
