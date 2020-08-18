package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

public class ReportingTest {

    private Reporting sut;

//    @Test
//    public void shouldOutputIdAndAmountWhenAccountReportExecuted() {
//        Account dummyAccount = mock(Account.class);
//        sut = new Reporting();
//
//        sut.getAccountReport(dummyAccount);
//
//        verify(dummyAccount).getId();
//        verify(dummyAccount).getAmount();
//        //проверить что в sut вызвался метод println если нужно
//    }

    @Test
    public void shouldOutputAllClientAccountsWhenClientReportExecuted() {
        Client dummyClient = spy(new Client (1,"dummy client name"));
        Account firstAccount = mock(Account.class);
        Account secondAccount = mock(Account.class);
        dummyClient.addAccount(firstAccount);
        dummyClient.addAccount(secondAccount);

        sut = new Reporting();
        sut.getClientReport(dummyClient);

//        verify(dummyClient).getAccounts();
        verify(firstAccount,times(1)).getAccountReport();
        verify(secondAccount,times(1)).getAccountReport();
        assertEquals(2,dummyClient.getAccounts().size());


    }
}
