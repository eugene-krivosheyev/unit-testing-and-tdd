package com.acme.banking.dbo;

import com.acme.banking.dbo.dal.AccountNotFoundException;
import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.AuditService;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ReportingTest {
    @Test
    public void shouldGetReportWhenClientExistsInRepo() throws AccountNotFoundException {
        AccountRepository accountsStub = mock(AccountRepository.class);
        Account accountStub = mock(Account.class);
        final UUID accountID = UUID.randomUUID();
        final UUID clientID = UUID.randomUUID();
        when(accountStub.getId()).thenReturn(accountID);
        when(accountStub.getClientId()).thenReturn(clientID);
        when(accountsStub.findById(accountID)).thenReturn(accountStub);
        AuditService auditDummy = mock(AuditService.class);
        final Reporting sut = new Reporting(accountsStub, auditDummy);

        final String report = sut.getReport(accountStub);

        assertThat(report)
                .isNotEmpty()
                .contains("## ")
                .contains(accountID.toString())
                .contains(clientID.toString());
    }

    @Test
    public void shouldLogReportingEventWhenReportRequested() throws AccountNotFoundException {
        Account accountDummy = mock(Account.class);
//        Account accountSpy = spy(accRepo);
        AccountRepository accountsDummy = mock(AccountRepository.class);
        when(accountsDummy.findById(any(UUID.class))).thenReturn(accountDummy);
        final UUID accountId = UUID.randomUUID();
        when(accountDummy.getId()).thenReturn(accountId);
        when(accountDummy.getClientId()).thenReturn(UUID.randomUUID());
        //doReturn("qq").when(accountDummy.getId());

        AuditService auditMock = mock(AuditService.class);
        final Reporting sut = new Reporting(accountsDummy, auditMock);

        sut.getReport(accountDummy);

        verify(auditMock, times(1))
                .log("report for accountid " + accountId);
    }

    @Test
    public void shouldReturnNewResultAfterInsert() throws AccountNotFoundException {
        AccountRepository accountsDummy = mock(AccountRepository.class);
        when(accountsDummy.findById(any()))
                .thenReturn(null)
                .thenReturn(new SavingAccount(null, null, 0))
                .thenThrow(new IllegalArgumentException());
    }
}
