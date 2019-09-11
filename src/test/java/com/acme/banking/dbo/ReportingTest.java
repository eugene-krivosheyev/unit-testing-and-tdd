package com.acme.banking.dbo;

import com.acme.banking.dbo.dal.AccountNotFoundException;
import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        final Reporting sut = new Reporting(accountsStub);

        final String report = sut.getReport(accountStub);

        assertThat(report)
                .isNotEmpty()
                .contains("## ")
                .contains(accountID.toString())
                .contains(clientID.toString());
    }
}
