package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    public void shouldWithdrawFromAndChargeToWhenTransfer() {
//    Given
        Processing sut = new Processing();
        SavingAccount fromAccountMock = mock(SavingAccount.class);
        SavingAccount toAccountMock = mock(SavingAccount.class);
        Double amount = 1.0;

//    When
        sut.transfer(amount,fromAccountMock,toAccountMock);

//    Then
        verify(fromAccountMock, times(1)).withdraw(amount);
        verify(toAccountMock, times(1)).charge(amount);
    }
}
