package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportingTest {

    @Test
    public void shouldGetReportWhenBranchWithAccountsWithClients() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);

        assertEquals(
                "# Branch #1" +
                        "## Account #1 (100.)" +
                        "### Client #1" +
                        "### Client #1" +
                        "### Client #1" +
                        "## Account #2 (100.)",
                sut.getReport(branchStub)
        );
    }

    @Test
    void shouldReturnReportWithEmptyBranch_when_branchIsEmpty() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);
        when(branchStub.getName()).thenReturn("1");
        when(branchStub.getAccounts()).thenReturn(Collections.emptyList());

        String report = sut.getReport(branchStub);

        assertEquals("# Branch #1", report);
    }
    
    @Test
    void shouldReturnReportWithAllAccountsInBranch_when_branchHasAccounts() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);
        Account account1 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client1"),  100.0d);
        Account account2 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client2"),  200.0d);

        List<Account> accounts = Lists.newArrayList(account1, account2);

        when(branchStub.getName()).thenReturn("1");
        when(branchStub.getAccounts()).thenReturn(accounts);

        String report = sut.getReport(branchStub);

        assertEquals(
                "# Branch #1\n" +
                        "## Account #" + account1.getId() + " (100.0)\n" +
                        "## Account #" + account2.getId() + " (200.0)",
                sut.getReport(branchStub)
        );
    }

//    @Test
//    void shouldGetReportWhenBranchWithAccountsWithNoClients() {
//        Reporting reporting = new Reporting();
//        Branch branch = mock(Branch.class);
//        when(branch.getName()).thenReturn("Branch #1");
//        Account accountStub1 = mock(Account.class);
//        Account accountStub2 = mock(Account.class);
//        when(branch.getAccounts()).thenReturn(Lists.newArrayList(
//                accountStub1, accountStub2
//        ));
//        UUID uuid1 = UUID.randomUUID();
//        when(accountStub1.getId()).thenReturn(uuid1);
//        when(accountStub1.getAmount()).thenReturn(100.0d);
//        UUID uuid2 = UUID.randomUUID();
//        when(accountStub2.getId()).thenReturn(uuid2);
//        when(accountStub2.getAmount()).thenReturn(200.0d);
//
//        String report = reporting.getReport(branch);
//
//        assertThat(report).isEqualTo(
//                        "# Branch #1" +
//                        "## Account #" + uuid1.toString() + " (100.0)" +
//                        "## Account #" + uuid2.toString() + " (200.0)"
//        );
//    }
}
