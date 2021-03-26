package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportingTest {

    @Test
    void shouldGetReportWhenBranchWithAccountsWithNoClients() {
        Reporting reporting = new Reporting();
        Branch branch = mock(Branch.class);
        when(branch.getName()).thenReturn("Branch #1");
        Account accountStub1 = mock(Account.class);
        Account accountStub2 = mock(Account.class);
        when(branch.getAccounts()).thenReturn(Lists.newArrayList(
                accountStub1, accountStub2
        ));
        UUID uuid1 = UUID.randomUUID();
        when(accountStub1.getId()).thenReturn(uuid1);
        when(accountStub1.getAmount()).thenReturn(100.0d);
        UUID uuid2 = UUID.randomUUID();
        when(accountStub2.getId()).thenReturn(uuid2);
        when(accountStub2.getAmount()).thenReturn(200.0d);

        String report = reporting.getReport(branch);

        assertThat(report).isEqualTo(
                        "# Branch #1" +
                        "## Account #" + uuid1.toString() + " (100.0)" +
                        "## Account #" + uuid2.toString() + " (200.0)"
        );
    }
}